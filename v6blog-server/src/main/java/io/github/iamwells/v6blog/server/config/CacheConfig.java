package io.github.iamwells.v6blog.server.config;

import io.github.iamwells.v6blog.server.cache.UserLoginIdKeyGenerator;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public KeyGenerator userLoginIdKeyGenerator() {
        return new UserLoginIdKeyGenerator();
    }
}
