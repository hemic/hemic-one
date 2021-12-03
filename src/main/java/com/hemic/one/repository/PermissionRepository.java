package com.hemic.one.repository;

import com.hemic.one.domain.Permission;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author jor
 * @create 2021/11/24 17:30
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

    Optional<Permission> findByAuthorizationUrl(String path);

}
