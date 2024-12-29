package io.github.iamwells.v6blog.server.dto;

import cn.dev33.satoken.secure.BCrypt;
import io.github.iamwells.v6blog.server.entity.AuthUser;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record AuthUserUpdateDTO(
        @Size(message = "用户名长度为2-32位！", min = 2, max = 32) String username,
        @Size(message = "密码长度为6-32位！", min = 6, max = 32) String password,
        @Size(min = 2, max = 32, message = "昵称长度为2-32位！") String nickname,
        @Size(message = "真实名称长度为2-64位！", min = 2, max = 64) String realName,
        @PositiveOrZero(message = "性别参数不合法，0代表女性，1代表男性！") @Max(value = 1, message = "性别参数不合法，0代表女性，1代表男性！") Short gender,
        @Size(message = "国家代码使用alpha-3版本（3位字母）！", min = 3, max = 3) String nationality,
        @Size(message = "民族代码为两位数字字符串！", min = 2, max = 2) String ethnicity,
        @Size(message = "国家代码使用alpha-3版本（3位字母）！", min = 3, max = 3) String country,
        @Size(message = "省份名称最长255位！", max = 255) String province,
        @Size(message = "州名称最长255位！", max = 255) String state,
        @Size(message = "城市名称最长255位！", max = 255) String city,
        @Size(message = "住址最长255位！", max = 255) String address,
        @Size(message = "邮政编码名称最长16位！", max = 16) String postalCode,
        @Size(message = "手机号码国家代码使用alpha-2（2位字母）！", min = 2, max = 2) String mobilePhoneCountry,
        @Size(message = "号码带区号，最长15位！", max = 15) String mobilePhone,
        @Size(message = "邮箱长度为5-254位！", min = 5, max = 254) String email
) implements Serializable {

    public AuthUser toAuthUser() {
        return new AuthUser(null, username, BCrypt.hashpw(password, BCrypt.gensalt()), nickname, realName, gender, nationality, ethnicity, country, province, state, city, address, postalCode, mobilePhone, email, null, null, null, null, null, null);
    }
}
