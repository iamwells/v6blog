package io.github.iamwells.v6blog.server.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sa-Token 配置类
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
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
}


