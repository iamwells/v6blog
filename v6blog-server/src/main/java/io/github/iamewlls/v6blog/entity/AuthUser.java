package io.github.iamewlls.v6blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "auth_user", schema = "v6blog_auth", uniqueConstraints = {
        @UniqueConstraint(name = "uk_au_username", columnNames = {"username"}),
        @UniqueConstraint(name = "uk_au_nickname", columnNames = {"nickname"}),
        @UniqueConstraint(name = "uk_au_mobile_phone", columnNames = {"mobile_phone"}),
        @UniqueConstraint(name = "uk_au_email", columnNames = {"email"})
})
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_user_id_gen")
    @SequenceGenerator(name = "auth_user_id_gen", sequenceName = "seq_auth_user", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 32)
    @NotNull
    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Size(max = 60)
    @NotNull
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Size(max = 32)
    @NotNull
    @Column(name = "nickname", nullable = false, length = 32)
    private String nickname;

    @Size(max = 64)
    @Column(name = "real_name", length = 64)
    private String realName;

    @Column(name = "gender")
    private Short gender;

    @Size(max = 3)
    @Column(name = "nationality", length = 3)
    private String nationality;

    @Size(max = 2)
    @Column(name = "ethnicity", length = 2)
    private String ethnicity;

    @Size(max = 3)
    @Column(name = "country", length = 3)
    private String country;

    @Size(max = 255)
    @Column(name = "province")
    private String province;

    @Size(max = 255)
    @Column(name = "state")
    private String state;

    @Size(max = 255)
    @Column(name = "city")
    private String city;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 16)
    @Column(name = "postal_code", length = 16)
    private String postalCode;

    @Size(max = 15)
    @Column(name = "mobile_phone", length = 15)
    private String mobilePhone;

    @Size(max = 254)
    @Column(name = "email", length = 254)
    private String email;

    @Size(max = 512)
    @Column(name = "avatar", length = 512)
    private String avatar;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Short status;

    @Column(name = "last_login")
    private OffsetDateTime lastLogin;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "is_deleted", nullable = false)
    private Short isDeleted;

}