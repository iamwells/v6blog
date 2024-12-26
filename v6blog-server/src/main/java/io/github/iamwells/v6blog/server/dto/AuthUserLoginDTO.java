package io.github.iamwells.v6blog.server.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthUserLoginDTO(@NotEmpty(message = "用户名不能为空") @Size(max = 32, min = 2) String username,
                               @NotEmpty(message = "密码不能为空") @Size(max = 32, min = 4) String password,
                               boolean rememberMe, @NotEmpty(message = "登录设备不能为空") String device) {
}
