package io.github.iamwells.v6blog.server.vo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthUserLoginVo {
    @NotEmpty(message = "用户名不能为空")
    @Size(max = 32, min = 2)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 32, min = 4)
    private String password;

    private boolean rememberMe;

    @NotEmpty(message = "登录设备不能为空")
    private String device;
}
