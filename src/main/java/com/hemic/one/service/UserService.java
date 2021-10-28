package com.hemic.one.service;

import com.hemic.one.config.Constants;
import com.hemic.one.constants.ErrorConstants;
import com.hemic.one.domain.Authority;
import com.hemic.one.domain.User;
import com.hemic.one.repository.AuthorityRepository;
import com.hemic.one.repository.UserRepository;
import com.hemic.one.security.AuthoritiesConstants;
import com.hemic.one.security.SecurityUtils;
import com.hemic.one.service.dto.AdminUserDTO;
import com.hemic.one.service.dto.UserDTO;
import com.hemic.one.utils.Assert;
import com.hemic.one.web.rest.vm.ManagedUserVM;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.security.RandomUtil;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public void activateRegistration(String key) {
        Optional<User> optional = userRepository.findOneByActivationKey(key);
        Assert.isPresent(optional, ErrorConstants.INVALID_ACTIVATION_KEY);
        log.debug("Activating user for activation key {}", key);
        optional.ifPresent(user -> {
            user.setActivated(true);
            user.setActivationKey(null);
            log.debug("Activated user: {}", user);
        });
    }

    public void completePasswordReset(String newPassword, String key) {
        Assert.isFalse(isPasswordLengthInvalid(newPassword), ErrorConstants.INVALID_PASSWORD);
        Optional<User> optional = userRepository
            .findOneByResetKey(key)
            .filter(user -> user.getResetDate().isAfter(Instant.now().minus(1, ChronoUnit.DAYS)));
        Assert.isPresent(optional, ErrorConstants.USER_NOT_FOUND_BY_RESET_KEY);
        User user = optional.get();
        log.debug("Reset user password for reset key {}", key);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetKey(null);
        user.setResetDate(null);
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository
            .findOneByEmailIgnoreCase(mail)
            .filter(User::isActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                return user;
            });
    }

    public User registerUser(AdminUserDTO userDTO, String password) {
        Assert.isFalse(isPasswordLengthInvalid(password), ErrorConstants.INVALID_PASSWORD);
        userRepository
            .findOneByLogin(userDTO.getLogin().toLowerCase())
            .ifPresent(existingUser -> {
                boolean removed = removeNonActivatedUser(existingUser);
                Assert.isTrue(removed, ErrorConstants.LOGIN_ALREADY_USED);
            });
        userRepository
            .findOneByEmailIgnoreCase(userDTO.getEmail())
            .ifPresent(existingUser -> {
                boolean removed = removeNonActivatedUser(existingUser);
                Assert.isTrue(removed, ErrorConstants.EMAIL_ALREADY_USED);
            });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        if (userDTO.getEmail() != null) {
            newUser.setEmail(userDTO.getEmail().toLowerCase());
        }
        newUser.setImageUrl(userDTO.getImageUrl());
        newUser.setLangKey(userDTO.getLangKey());
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    private boolean removeNonActivatedUser(User existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        return true;
    }

    public User createUser(AdminUserDTO userDTO) {

        Assert.isNotPresent(userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()), ErrorConstants.LOGIN_ALREADY_USED);
        Assert.isNotPresent(userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()), ErrorConstants.EMAIL_ALREADY_USED);

        User user = new User();
        user.setLogin(userDTO.getLogin().toLowerCase());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail().toLowerCase());
        }
        user.setImageUrl(userDTO.getImageUrl());

        if (userDTO.getLangKey() == null) {
            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = userDTO
                .getAuthorities()
                .stream()
                .map(authorityRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update.
     * @return updated user.
     */
    public Optional<AdminUserDTO> updateUser(AdminUserDTO userDTO) {
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        Assert.isFalse(existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId())), ErrorConstants.EMAIL_ALREADY_USED);
        existingUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());
        Assert.isFalse(existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId())), ErrorConstants.LOGIN_ALREADY_USED);

        return Optional
            .of(userRepository.findById(userDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                user.setLogin(userDTO.getLogin().toLowerCase());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                if (userDTO.getEmail() != null) {
                    user.setEmail(userDTO.getEmail().toLowerCase());
                }
                user.setImageUrl(userDTO.getImageUrl());
                user.setActivated(userDTO.isActivated());
                user.setLangKey(userDTO.getLangKey());
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                userDTO
                    .getAuthorities()
                    .stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(managedAuthorities::add);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(AdminUserDTO::new);
    }

    public void deleteUser(String login) {
        userRepository
            .findOneByLogin(login)
            .ifPresent(user -> {
                userRepository.delete(user);
                log.debug("Deleted User: {}", user);
            });
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user.
     * @param lastName  last name of user.
     * @param email     email id of user.
     * @param langKey   language key.
     * @param imageUrl  image URL of user.
     */
    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();
        Assert.isPresent(login, ErrorConstants.USER_NOT_FOUND);
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(email);
        Assert.isFalse(existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(login.get())), ErrorConstants.EMAIL_ALREADY_USED);
        Optional<User> userOptional = userRepository.findOneByLogin(login.get());
        Assert.isPresent(userOptional, ErrorConstants.USER_NOT_FOUND);
        User user = userOptional.get();
        user.setFirstName(firstName);
        user.setLastName(lastName);


    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String currentClearTextPassword, String newPassword) {
        Assert.isFalse(isPasswordLengthInvalid(newPassword), ErrorConstants.INVALID_PASSWORD);
        SecurityUtils
            .getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                String currentEncryptedPassword = user.getPassword();
                Assert.isTrue(passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword), ErrorConstants.INVALID_PASSWORD);
                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);
                log.debug("Changed password for User: {}", user);
            });
    }

    @Transactional(readOnly = true)
    public Page<AdminUserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(AdminUserDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllPublicUsers(Pageable pageable) {
        return userRepository.findAllByIdNotNullAndActivatedIsTrue(pageable).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public AdminUserDTO getUserWithAuthorities() {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();
        Assert.isPresent(login, ErrorConstants.USER_NOT_FOUND);
        Optional<User> user = userRepository.findOneWithAuthoritiesByLogin(login.get());
        Assert.isPresent(user, ErrorConstants.USER_NOT_FOUND);
        return new AdminUserDTO(user.get());
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        userRepository
            .findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))
            .forEach(user -> {
                log.debug("Deleting not activated user {}", user.getLogin());
                userRepository.delete(user);
            });
    }

    /**
     * Gets a list of all the authorities.
     *
     * @return a list of all the authorities.
     */
    @Transactional(readOnly = true)
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }


    private static boolean isPasswordLengthInvalid(String password) {
        return (
            StringUtils.isEmpty(password) ||
                password.length() < ManagedUserVM.PASSWORD_MIN_LENGTH ||
                password.length() > ManagedUserVM.PASSWORD_MAX_LENGTH
        );
    }
}
