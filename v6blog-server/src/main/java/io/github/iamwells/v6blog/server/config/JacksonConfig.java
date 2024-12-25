package io.github.iamwells.v6blog.server.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Jackson配置类，用于全局定制Jackson序列化和反序列化行为
 */
@Configuration
public class JacksonConfig {
    /**
     * 创建并配置一个Jackson2ObjectMapperBuilderCustomizer对象
     * 该对象用于定制ObjectMapper的构建过程，以实现全局配置
     * 主要目的是为特定类型配置序列化方式，以确保这些类型在序列化时的一致性和正确性
     *
     * @return Jackson2ObjectMapperBuilderCustomizer对象，用于定制ObjectMapper的构建过程
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        // 返回一个自定义的Jackson2ObjectMapperBuilderCustomizer
        return builder -> {
            // 为Long类型配置序列化器，确保在序列化时转换为字符串
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            // 为long基本类型配置序列化器，确保在序列化时转换为字符串
            builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            // 为BigInteger类型配置序列化器，确保在序列化时转换为字符串
            builder.serializerByType(BigInteger.class, ToStringSerializer.instance);
            // 为BigDecimal类型配置序列化器，确保在序列化时转换为字符串
            builder.serializerByType(BigDecimal.class, ToStringSerializer.instance);
        };
    }
}

