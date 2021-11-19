package com.hemic.one.security;


import com.hemic.one.constants.ErrorConstants;
import com.hemic.one.repository.UserRepository;
import com.hemic.one.service.UserService;
import com.hemic.one.service.mapper.UserMapper;
import com.hemic.one.utils.AuthenticationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserService userService;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public DomainUserDetailsService(UserService userService, UserRepository userRepository, UserMapper userMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(final String login) {
        UserDetails details = userService.getByUsername(login);
        AuthenticationUtils.isUsernameValid(details != null, ErrorConstants.USER_NOT_FOUND);
        return details;
    }


}
