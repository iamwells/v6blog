package io.github.iamwells.v6blog.server.service;

import io.github.iamwells.v6blog.server.dto.AuthUserLoginDTO;
import io.github.iamwells.v6blog.server.dto.AuthUserRegisterDTO;


/**
 * 认证用户服务接口
 * 提供用户登录和注册的方法
 */
public interface AuthUserService {

    /**
     * 执行用户登录操作
     *
     * @param dto 用户登录信息封装对象，包含登录所需的用户名和密码等信息
     * @return 登录结果字符串，通常用于表示登录成功或失败的信息
     */
    String doLogin(AuthUserLoginDTO dto);

    /**
     * 执行用户注册操作
     *
     * @param dto 用户注册信息封装对象，包含注册所需的用户名、密码、昵称等信息
     * @return
     */
    String doRegister(AuthUserRegisterDTO dto);
}
