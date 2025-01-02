package io.github.iamwells.v6blog.server.config;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.iamwells.v6blog.server.entity.QAuthRole;
import io.github.iamwells.v6blog.server.entity.QAuthUser;
import io.github.iamwells.v6blog.server.entity.QRUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Sa-Token 配置类
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {


    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    /**
     * 注册 Sa-Token 拦截器，打开注解式鉴权功能
     * 此方法告诉 Spring Boot 如何配置和使用 Sa-Token 拦截器
     * 它将拦截器应用到所有请求路径，从而实现全局鉴权
     *
     * @param registry 拦截器注册器，用于注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public StpInterface stpInterface() {
        return new StpInterface() {
            @Override
            public List<String> getPermissionList(Object loginId, String loginType) {
                return List.of();
            }

            @Override
            public List<String> getRoleList(Object loginId, String loginType) {
                List<String> roleList = (List<String>) SaManager.getSaTokenDao().getObject(StpUtil.getTokenName() + ":authorize:user-roles:" + loginId);
                if (roleList == null) {
                    QAuthRole authRole = QAuthRole.authRole;
                    QRUserRole rUserRole = QRUserRole.rUserRole;
                    QAuthUser authUser = QAuthUser.authUser;
                    if (loginId != null) {
                        Long id = Long.parseLong(loginId.toString());
                        roleList = jpaQueryFactory.select(authRole.code)
                                .from(authUser)
                                .innerJoin(rUserRole).on(rUserRole.userId.eq(authUser.id))
                                .innerJoin(authRole).on(rUserRole.roleId.eq(authRole.id))
                                .where(authUser.id.eq(id))
                                .fetch();
                        SaManager.getSaTokenDao().setObject(StpUtil.getTokenName() + ":authorize:user-roles:" + loginId, roleList, 60 * 60 * 24 * 15);
                    }
                }
                return roleList;
            }
        };
    }
}


