package io.github.iamwells.v6blog.server.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.iamwells.v6blog.server.dto.AuthUserDTO;
import io.github.iamwells.v6blog.server.entity.AuthUser;
import io.github.iamwells.v6blog.server.entity.QAuthUser;
import io.github.iamwells.v6blog.server.repository.AuthUserRepository;
import io.github.iamwells.v6blog.server.vo.AuthUserLoginVo;
import io.github.iamwells.v6blog.server.vo.AuthUserQueryVO;
import io.github.iamwells.v6blog.server.vo.AuthUserUpdateVO;
import io.github.iamwells.v6blog.server.vo.AuthUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;

@Service
@Transactional
public class AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public Long save(AuthUserVO vO) {
        AuthUser bean = new AuthUser();
        BeanUtils.copyProperties(vO, bean);
        bean = authUserRepository.save(bean);
        return bean.getId();
    }

    public void delete(Long id) {
        authUserRepository.deleteById(id);
    }

    public void update(Long id, AuthUserUpdateVO vO) {
        AuthUser bean = requireOne(id);
        BeanUtil.copyProperties(vO, bean, CopyOptions.create().setIgnoreNullValue(true));
        String password = bean.getPassword();
        if (StringUtils.hasText(password)) {
            String encryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            bean.setPassword(encryptPassword);
        }
        authUserRepository.save(bean);
    }

    public AuthUserDTO getById(Long id) {
        AuthUser original = requireOne(id);
        return toDTO(original);
    }

    public Page<AuthUserDTO> query(AuthUserQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private AuthUserDTO toDTO(AuthUser original) {
        AuthUserDTO bean = new AuthUserDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private AuthUser requireOne(Long id) {
        return authUserRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public String doLogin(AuthUserLoginVo vO) {
        QAuthUser authUser = QAuthUser.authUser;
        AuthUser user = jpaQueryFactory.select(Projections.bean(AuthUser.class, authUser.id, authUser.password))
                .from(authUser)
                .where(authUser.username.eq(vO.getUsername()))
                .fetchOne();
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (BCrypt.checkpw(vO.getPassword(), user.getPassword())) {
            StpUtil.login(user.getId(), SaLoginModel.create().setIsLastingCookie(vO.isRememberMe()).setDevice(vO.getDevice()));
            return StpUtil.getTokenInfo().getTokenValue();
        }else{
            throw new RuntimeException("密码错误");
        }
    }
}
