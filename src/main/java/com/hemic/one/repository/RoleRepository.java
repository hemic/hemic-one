package com.hemic.one.repository;

import com.hemic.one.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author jor
 * @create 2021/11/24 17:28
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findOneByName(String name);
}
