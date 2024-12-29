package io.github.iamwells.v6blog.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.type.NumericBooleanConverter;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth_user", schema = "v6blog_auth")
@SoftDelete(columnName = "is_deleted", converter = NumericBooleanConverter.class)
public class AuthUser implements Serializable {

    @Serial
    private static final long serialVersionUID = -4225485631725128107L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_user_id_gen")
    @SequenceGenerator(name = "auth_user_id_gen", schema = "v6blog_auth", sequenceName = "seq_auth_user", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 32)
    @NotNull
    @Column(name = "username", nullable = false, unique = true, length = 32)
    private String username;

    @Size(max = 60)
    @NotNull
    @Column(name = "password", nullable = false, length = 60)
    @JsonIgnore
    private String password;

    @Size(max = 32)
    @NotNull
    @Column(name = "nickname", nullable = false, unique = true, length = 32)
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
    @Column(name = "mobile_phone", unique = true, length = 15)
    private String mobilePhone;

    @Size(max = 254)
    @Column(name = "email", unique = true, length = 254)
    private String email;

    @Size(max = 512)
    @Column(name = "avatar", length = 512)
    private String avatar;

    @Column(name = "status", nullable = false, insertable = false)
    @JsonIgnore
    private Short status;

    @Column(name = "last_login")
    private OffsetDateTime lastLogin;

    @Column(name = "create_time", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createTime;

    @Column(name = "update_time", nullable = false, insertable = false)
    private OffsetDateTime updateTime;

    @Column(name = "is_deleted", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Short isDeleted;

    @PreUpdate
    public void preUpdate() {
        if (this.status == null) {
            this.status = 1;
        }
        OffsetDateTime now = OffsetDateTime.now();
        this.updateTime = now;
        this.lastLogin = now;
    }

}