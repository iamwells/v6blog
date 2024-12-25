package io.github.iamwells.v6blog.server.controller;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.config.SaCookieConfig;
import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.github.iamwells.v6blog.server.dto.AuthUserDTO;
import io.github.iamwells.v6blog.server.service.AuthUserService;
import io.github.iamwells.v6blog.server.vo.AuthUserLoginVo;
import io.github.iamwells.v6blog.server.vo.AuthUserQueryVO;
import io.github.iamwells.v6blog.server.vo.AuthUserUpdateVO;
import io.github.iamwells.v6blog.server.vo.AuthUserVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
     * 保存用户信息
     *
     * @param vO 用户信息对象，包含需保存的用户数据
     * @return 返回保存操作的结果，通常是一个标识操作成功的字符串
     */
    @PostMapping
    public String save(@Valid @RequestBody AuthUserVO vO) {
        return authUserService.save(vO).toString();
    }

    /**
     * 删除用户信息
     *
     * @param id 需要删除的用户ID
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission(value = "USER_DELETE", orRole = "ROLE_SUPER")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        authUserService.delete(id);
    }

    /**
     * 更新用户信息
     *
     * @param id 需要更新的用户ID
     * @param vO 包含更新信息的用户对象
     */
    @PutMapping("/{id}")
    @SaCheckPermission(value = "USER_UPDATE", orRole = "ROLE_SUPER")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @NotNull @RequestBody AuthUserUpdateVO vO) {
        authUserService.update(id, vO);
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 需要查询的用户ID
     * @return 返回查询到的用户信息对象
     */
    @GetMapping("/{id}")
    @SaCheckPermission(value = "USER_QUERY", orRole = "ROLE_SUPER")
    public AuthUserDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return authUserService.getById(id);
    }

    /**
     * 查询用户列表
     *
     * @param vO 包含查询条件的用户对象
     * @return 返回查询到的用户信息列表，以分页形式返回
     */
    @GetMapping
    @SaCheckPermission(value = "USER_QUERY", orRole = "ROLE_SUPER")
    public Page<AuthUserDTO> query(@Valid AuthUserQueryVO vO) {
        return authUserService.query(vO);
    }

    /**
     * 用户登录
     *
     * @param vO 包含用户登录信息的对象
     * @return 返回登录结果，包括成功或错误信息
     */
    @PostMapping("/login")
    public SaResult login(@Valid @NotNull @RequestBody AuthUserLoginVo vO) {
        // 创建一个HashMap来存储登录结果，包括生成的token
        HashMap<String, String> result = new HashMap<>();
        // 调用业务服务方法执行用户登录，并将返回的token存入结果中
        result.put("token", authUserService.doLogin(vO));
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


}
