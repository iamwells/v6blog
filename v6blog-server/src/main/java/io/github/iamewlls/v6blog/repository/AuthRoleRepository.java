package io.github.iamewlls.v6blog.repository;

import io.github.iamewlls.v6blog.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Long>, JpaSpecificationExecutor<AuthRole> {
}