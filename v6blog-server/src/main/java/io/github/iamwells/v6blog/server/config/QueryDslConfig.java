package io.github.iamwells.v6blog.server.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Querydsl的配置类
 */
@Configuration
public class QueryDslConfig {

    /**
     * 创建并配置JPAQueryFactory bean
     *
     * @param em EntityManager实例，用于实体管理和持久化操作
     * @return JPAQueryFactory实例，用于构建和执行查询
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em){
        return new JPAQueryFactory(em);
    }
}

