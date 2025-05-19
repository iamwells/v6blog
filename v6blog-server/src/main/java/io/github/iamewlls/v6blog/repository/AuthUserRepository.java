package io.github.iamewlls.v6blog.repository;

import io.github.iamewlls.v6blog.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>, JpaSpecificationExecutor<AuthUser> {
}