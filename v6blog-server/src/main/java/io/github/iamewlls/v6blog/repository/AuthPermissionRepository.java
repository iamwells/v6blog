package io.github.iamewlls.v6blog.repository;

import io.github.iamewlls.v6blog.entity.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Long>, JpaSpecificationExecutor<AuthPermission> {
}