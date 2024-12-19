package io.github.iamwells.v6blog.server.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "auth_user",schema = "v6blog_auth")
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nickname", nullable = false)
    private String nickname;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 性别（0代表女，1代表男）
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 国籍
     */
    @Column(name = "nationality")
    private String nationality;

    /**
     * 民族（中国名族代码，参照名族代码）
     */
    @Column(name = "ethnicity")
    private Integer ethnicity;

    /**
     * 所在国家（国家代码）
     */
    @Column(name = "country")
    private String country;

    /**
     * 所在省份
     */
    @Column(name = "province")
    private String province;

    /**
     * 所在州
     */
    @Column(name = "state")
    private String state;

    /**
     * 所在城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 住址
     */
    @Column(name = "address")
    private String address;

    /**
     * 邮政编码
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * 手机号码
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 固定电话号码
     */
    @Column(name = "fixed_phone")
    private String fixedPhone;

    /**
     * 电子邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 用户状态
     */
    @Column(name = "status", nullable = false)
    private Integer status;

    /**
     * 最近一次登录时间
     */
    @Column(name = "last_login")
    private OffsetDateTime lastLogin;

    /**
     * 注册时间
     */
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;

}
