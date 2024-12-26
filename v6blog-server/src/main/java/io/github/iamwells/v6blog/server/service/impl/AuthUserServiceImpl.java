package io.github.iamwells.v6blog.server.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.iamwells.v6blog.server.dto.AuthUserLoginDTO;
import io.github.iamwells.v6blog.server.entity.AuthUser;
import io.github.iamwells.v6blog.server.entity.QAuthUser;
import io.github.iamwells.v6blog.server.repository.AuthUserRepository;
import io.github.iamwells.v6blog.server.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
