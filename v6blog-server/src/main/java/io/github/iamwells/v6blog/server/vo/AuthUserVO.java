package io.github.iamwells.v6blog.server.vo;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class AuthUserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    @Size(max = 32, min = 2)
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Size(max = 32, min = 4, message = "密码长度不符合要求")
    private String password;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别（0代表女，1代表男）
     */
    @Max(value = 1, message = "性别只能为男1或女0")
    @Min(value = 0, message = "性别只能为男1或女0")
    private Integer gender;

    /**
     * 国籍
     */
    @Size(max = 3, min = 3, message = "国籍代码不符合要求")
    private String nationality;

    /**
     * 民族（中国名族代码，参照名族代码）
     */
    @Size(max = 2, min = 2, message = "民族代码不符合要求")
    private String ethnicity;

    /**
     * 所在国家（国家代码）
     */
    @Size(max = 3, min = 3, message = "国家代码不符合要求")
    private String country;

    /**
     * 所在省份
     */
    @Size(max = 255, message = "省份长度不符合要求")
    private String province;

    /**
     * 所在州
     */
    @Size(max = 255, message = "州名长度不符合要求")
    private String state;

    /**
     * 所在城市
     */
    @Size(max = 255, message = "城市名长度不符合要求")
    private String city;

    /**
     * 住址
     */
    @Size(max = 255, message = "住址长度不符合要求")
    private String address;

    /**
     * 邮政编码
     */
    @Size(max = 32, message = "邮政编码长度不符合要求")
    private String postalCode;

    /**
     * 手机号码
     */
    @Size(max = 15, min = 7, message = "手机号码长度不符合要求")
    private String mobilePhone;

    /**
     * 固定电话号码
     */
    @Size(max = 15, message = "固定号码长度不符合要求")
    private String fixedPhone;

    /**
     * 电子邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 254, min = 6, message = "邮箱长度不符合要求")
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户状态
     */
    @NotNull(message = "用户状态不能为空")
    private Integer status;

    /**
     * 最近一次登录时间
     */
    private OffsetDateTime lastLogin;

    /**
     * 注册时间
     */
    @NotNull(message = "注册时间不能为空")
    private OffsetDateTime createTime;

    /**
     * 更新时间
     */
    @NotNull(message = "更新时间不能为空")
    private OffsetDateTime updateTime;
}
