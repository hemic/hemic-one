package com.hemic.one.security;


import com.hemic.one.constants.ErrorConstants;
import com.hemic.one.domain.User;
import com.hemic.one.repository.UserRepository;
import com.hemic.one.utils.AuthenticationUtils;
import java.util.HashSet;
import java.util.Optional;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
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

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(final String login) {
        Optional<User> optional = new EmailValidator().isValid(login, null) ? userRepository.findOneByEmailIgnoreCase(login) : userRepository.findOneByUserName(login);
        AuthenticationUtils.isUsernameValid(optional.isPresent(), ErrorConstants.USER_NOT_FOUND);
        return createUser(optional.get().getUserName(), optional.get().getPassword());
    }

    private org.springframework.security.core.userdetails.User createUser(String username, String password) {
        return new org.springframework.security.core.userdetails.User(username, password, new HashSet<>());
    }


}
