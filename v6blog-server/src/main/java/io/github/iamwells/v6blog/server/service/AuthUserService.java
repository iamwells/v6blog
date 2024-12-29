package io.github.iamwells.v6blog.server.service;

import io.github.iamwells.v6blog.server.dto.AuthUserLoginDTO;
import io.github.iamwells.v6blog.server.dto.AuthUserRegisterDTO;
import io.github.iamwells.v6blog.server.dto.AuthUserUpdateDTO;
import io.github.iamwells.v6blog.server.entity.AuthUser;


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

    /**
     * 执行用户注销操作
     *
     * @param loginId 用户登录标识，用于标识当前用户
     */
    void doDelete(Object loginId);

    /**
     * 更新用户信息的方法
     * 此方法通过接收一个包含用户更新信息的数据传输对象来更新用户信息
     * 主要用于处理用户信息的更新操作，如修改用户名、密码、邮箱等
     *
     * @param loginId 用户登录标识，用于标识当前用户
     * @param dto     包含用户更新信息的数据传输对象，用于指定要更新的用户信息
     * @return
     */
    AuthUser doUpdate(Object loginId, AuthUserUpdateDTO dto);

    /**
     * 获取用户信息的方法
     * 此方法通过接收一个用户登录标识来获取用户信息
     * 主要用于获取用户信息，如用户名、密码、邮箱等
     *
     * @param loginId 用户登录标识，用于标识当前用户
     * @return 包含用户信息的实体对象，用于返回用户信息
     */
    AuthUser doGetUserInfo(Object loginId);
}
