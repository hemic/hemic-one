package com.hemic.one.repository;

import com.hemic.one.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link User} entity.
 *
 * @author jor
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {


    /**
     * find user by email. 根据email地址 查询用户
     *
     * @param email
     * @return java.util.Optional<com.hemic.one.domain.User>
     * @author jor
     * @date 2021/11/9
     */
    Optional<User> findOneByEmailIgnoreCase(String email);


    Optional<User> findOneByUsername(String name);


}
