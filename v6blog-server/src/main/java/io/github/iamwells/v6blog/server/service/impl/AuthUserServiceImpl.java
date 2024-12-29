package io.github.iamwells.v6blog.server.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.iamwells.v6blog.server.dto.AuthUserLoginDTO;
import io.github.iamwells.v6blog.server.dto.AuthUserRegisterDTO;
import io.github.iamwells.v6blog.server.dto.AuthUserUpdateDTO;
import io.github.iamwells.v6blog.server.entity.AuthUser;
import io.github.iamwells.v6blog.server.entity.QAuthUser;
import io.github.iamwells.v6blog.server.repository.AuthUserRepository;
import io.github.iamwells.v6blog.server.service.AuthUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public String doLogin(AuthUserLoginDTO dto) {
        QAuthUser authUser = QAuthUser.authUser;
        AuthUser user = jpaQueryFactory.select(Projections.bean(AuthUser.class, authUser.id, authUser.password))
                .from(authUser)
                .where(authUser.username.eq(dto.username()))
                .fetchOne();
        if (user == null) {
            throw new RuntimeException("用户不存在！");
        }
        if (BCrypt.checkpw(dto.password(), user.getPassword())) {
            StpUtil.login(user.getId(), SaLoginModel.create().setIsLastingCookie(dto.rememberMe()).setDevice(dto.device()));
            return StpUtil.getTokenInfo().getTokenValue();
        } else {
            throw new RuntimeException("密码错误！");
        }
    }

    @Override
    public String doRegister(AuthUserRegisterDTO dto) {
        AuthUser user = dto.toAuthUser();
        AuthUser save = authUserRepository.save(user);
        return save.getId().toString();
    }

    @Override
    @CacheEvict(value = "user", keyGenerator = "userLoginIdKeyGenerator")
    public void doDelete(Object loginId) {
        if (loginId == null) {
            throw new RuntimeException("未找到当前用户登录id！请联系管理员处理！");
        }
        authUserRepository.deleteById((Long) loginId);
    }

    @Override
    @CachePut(value = "user", keyGenerator = "userLoginIdKeyGenerator")
    public AuthUser doUpdate(Object loginId, AuthUserUpdateDTO dto) {
        if (loginId == null) {
            throw new RuntimeException("未找到当前用户登录id！请联系管理员处理！");
        }
        AuthUser authUser = dto.toAuthUser();
        AuthUser bean = authUserRepository.findById(Long.valueOf(loginId.toString())).orElseThrow(
                () -> new EntityNotFoundException("用户不存在！")
        );
        BeanUtil.copyProperties(authUser, bean, CopyOptions.create().ignoreNullValue());
        authUserRepository.save(bean);
        return bean;
    }

    @Override
    @Cacheable(value = "user", keyGenerator = "userLoginIdKeyGenerator")
    public AuthUser doGetUserInfo(Object loginId) {
        AuthUser bean = authUserRepository.findById(Long.valueOf(loginId.toString())).orElseThrow(
                () -> new EntityNotFoundException("用户不存在！")
        );
        return bean;
    }
}
