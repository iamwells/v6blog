package io.github.iamwells.v6blog.server.cache;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class UserLoginIdKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return "id:" + StpUtil.getLoginId();
    }
}
