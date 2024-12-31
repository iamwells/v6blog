package io.github.iamwells.v6blog.server.config;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public KeyGenerator userLoginIdKeyGenerator() {
        return (target, method, params) -> "id:" + StpUtil.getLoginId();
    }
}
