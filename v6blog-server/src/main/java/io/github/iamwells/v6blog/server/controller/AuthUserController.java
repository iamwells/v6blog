package io.github.iamwells.v6blog.server.controller;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.config.SaCookieConfig;
import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.github.iamwells.v6blog.server.dto.AuthUserLoginDTO;
import io.github.iamwells.v6blog.server.dto.AuthUserRegisterDTO;
import io.github.iamwells.v6blog.server.dto.AuthUserUpdateDTO;
import io.github.iamwells.v6blog.server.entity.AuthUser;
import io.github.iamwells.v6blog.server.service.AuthUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 用户认证控制器，处理与用户认证相关的HTTP请求
 */
@Validated
@RestController
@RequestMapping("/auth/user")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    /**
     * 处理用户注册请求
     *
     * @param dto 经过验证的用户注册数据传输对象，确保数据有效性
     * @return 返回用户注册的结果，包括用户ID
     */
    @PostMapping("/register")
    public SaResult register(@Valid @NotNull @RequestBody AuthUserRegisterDTO dto) {
        // 调用业务服务方法执行用户注册
        String id = authUserService.doRegister(dto);
        // 创建一个数据容器来存储用户ID
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", id);
        // 返回成功结果，包含用户ID
        return SaResult.ok().setData(data);
    }

    /**
     * 处理用户删除请求的控制器方法
     * 该方法用于注销当前登录用户，并执行用户数据的删除操作
     */
    @DeleteMapping("")
    @SaCheckLogin
    public SaResult delete(HttpServletResponse response) {
        // 获取当前登录用户的ID
        Object loginId = StpUtil.getLoginId();
        // 执行删除操作，清除用户在当前设备上的登录状态
        StpUtil.logout(loginId);
        // 删除Cookie
        deleteCookie(response);
        // 调用业务服务方法执行用户删除操作
        authUserService.doDelete(loginId);
        // 返回成功结果
        return SaResult.ok();
    }

    /**
     * 处理用户更新请求的控制器方法
     * 该方法用于更新当前登录用户的信息
     *
     * @param dto 经过验证且非空的用户更新数据，通过请求体传递
     * @return 更新操作的结果，由SaResult封装
     */
    @PutMapping("")
    @SaCheckLogin
    public SaResult update(@Valid @NotNull @RequestBody AuthUserUpdateDTO dto) {
        // 获取当前登录用户的ID
        Object loginId = StpUtil.getLoginId();
        // 调用业务服务方法执行用户更新操作
        authUserService.doUpdate(loginId, dto);
        // 返回成功结果
        return SaResult.ok();
    }

    /**
     * 用户登录
     *
     * @param dto 包含用户登录信息的对象
     * @return 返回登录结果，包括成功或错误信息
     */
    @PostMapping("/login")
    public SaResult login(@Valid @NotNull @RequestBody AuthUserLoginDTO dto) {
        // 创建一个HashMap来存储登录结果，包括生成的token
        HashMap<String, String> result = new HashMap<>();
        // 调用业务服务方法执行用户登录，并将返回的token存入结果中
        result.put("token", authUserService.doLogin(dto));
        // 返回包含登录结果的SaResult对象
        return SaResult.data(result);
    }

    /**
     * 用户登出
     *
     * @param device 用户设备信息，用于在特定设备上执行登出操作
     * @return 返回操作结果，包括成功或错误信息
     */
    @PostMapping("/logout")
    @SaCheckLogin
    public SaResult logout(String device, HttpServletResponse response) {
        // 获取当前登录用户的ID
        Object loginId = StpUtil.getLoginId();
        // 执行登出操作，清除用户在特定设备上的登录状态
        if (StringUtils.hasText(device)) {
            // 执行登出操作，清除用户在特定设备上的登录状态
            StpUtil.logout(loginId, device);
        } else {
            // 执行登出操作，清除用户在当前设备上的登录状态
            StpUtil.logout(loginId);
        }
        // 删除Cookie
        deleteCookie(response);
        // 如果登出成功，返回成功结果
        return SaResult.ok();
    }

    /**
     * 删除用户的Cookie
     *
     * @param response HttpServletResponse对象，用于添加Cookie
     */
    private void deleteCookie(HttpServletResponse response) {
        // 获取SaToken配置
        SaTokenConfig config = SaManager.getConfig();
        // 获取Cookie配置
        SaCookieConfig cookieConfig = config.getCookie();
        // 获取Token名称
        String tokenName = config.getTokenName();
        // 获取Cookie路径
        String path = cookieConfig.getPath();
        // 如果Token名称为空，则默认为"satoken"
        if (!StringUtils.hasText(tokenName)) {
            tokenName = "satoken";
        }
        // 如果路径为空，则默认为"/"
        if (!StringUtils.hasText(path)) {
            path = "/";
        }
        // 创建一个空值的Cookie，名称为Token名称
        Cookie cookie = new Cookie(tokenName, null);
        // 设置Cookie路径
        cookie.setPath(path);
        // 设置Cookie最大存活时间为0，即立即删除
        cookie.setMaxAge(0);
        // 将Cookie添加到响应中
        response.addCookie(cookie);
    }

    @GetMapping("")
    @SaCheckLogin
    public SaResult getInfo() {
        // 获取当前登录用户的ID
        Object loginId = StpUtil.getLoginId();
        // 调用业务服务方法获取用户信息
        AuthUser user = authUserService.doGetUserInfo(loginId);
        // 返回包含用户信息的SaResult对象
        return SaResult.data(user);
    }

    @GetMapping("/permissions")
    @SaCheckLogin
    public SaResult getAllPermissions() {
        // 获取当前登录用户的ID
        List<String> roleList = StpUtil.getRoleList();
        return SaResult.data(roleList);
    }


}
