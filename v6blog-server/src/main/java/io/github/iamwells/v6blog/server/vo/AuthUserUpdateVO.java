package io.github.iamwells.v6blog.server.vo;


import cn.dev33.satoken.secure.BCrypt;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthUserUpdateVO extends AuthUserVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
