package io.github.iamwells.v6blog.server.dto;


import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class AuthUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;


    /**
     * 用户名
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 昵称
     */
    private String nickname;


    /**
     * 真实姓名
     */
    private String realName;


    /**
     * 性别（0代表女，1代表男）
     */
    private Integer gender;


    /**
     * 国籍
     */
    private String nationality;


    /**
     * 民族（中国名族代码，参照名族代码）
     */
    private Integer ethnicity;


    /**
     * 所在国家（国家代码）
     */
    private String country;


    /**
     * 所在省份
     */
    private String province;


    /**
     * 所在州
     */
    private String state;


    /**
     * 所在城市
     */
    private String city;


    /**
     * 住址
     */
    private String address;


    /**
     * 邮政编码
     */
    private String postalCode;


    /**
     * 手机号码
     */
    private String mobilePhone;


    /**
     * 固定电话号码
     */
    private String fixedPhone;


    /**
     * 电子邮箱
     */
    private String email;


    /**
     * 头像
     */
    private String avatar;


    /**
     * 用户状态
     */
    private Integer status;


    /**
     * 最近一次登录时间
     */
    private OffsetDateTime lastLogin;


    /**
     * 注册时间
     */
    private OffsetDateTime createTime;


    /**
     * 更新时间
     */
    private OffsetDateTime updateTime;

}
