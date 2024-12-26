package io.github.iamwells.v6blog.server.service;

import io.github.iamwells.v6blog.server.dto.AuthUserLoginDTO;

public interface AuthUserService {

    String doLogin(AuthUserLoginDTO vO);
}
