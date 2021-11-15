package com.hemic.one.service;

import com.hemic.common.utils.Assert;
import com.hemic.one.config.ApplicationProperties;
import com.hemic.one.constants.ErrorConstants;
import com.hemic.one.domain.User;
import com.hemic.one.repository.UserRepository;
import com.hemic.one.web.rest.vm.UserVm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author jor
 * @create 2021/11/9 14:59
 * <p>
 * Service class for managing users. 管理用户的服务类
 * <p/>
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationProperties applicationProperties;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ApplicationProperties applicationProperties) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationProperties = applicationProperties;
    }


    @Transactional(rollbackFor = Exception.class)
    public String create(UserVm vm) {
        Assert.isEmpty(userRepository.findOneByUserName(vm.getUserName()), ErrorConstants.LOGIN_ALREADY_USED);
        Assert.isEmpty(userRepository.findOneByEmailIgnoreCase(vm.getEmail()), ErrorConstants.EMAIL_ALREADY_USED);
        User user = new User(vm.getUserName(), vm.getFullName(), vm.getEmail(), vm.getManager(), vm.getTitle(), vm.getMobileNumber(), vm.getEmployeeNumber(), vm.getSeq(),
            vm.getIcon());
        user.generatorPassword(passwordEncoder, applicationProperties.getDefaultPassword());
        return userRepository.save(user).getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(String id, UserVm vm) {
        var optionalUser = userRepository.findById(id);
        Assert.notEmpty(optionalUser, ErrorConstants.USER_NOT_FOUND);
        if (!vm.getEmail().equals(optionalUser.get().getEmail())) {
            Assert.isEmpty(userRepository.findOneByEmailIgnoreCase(vm.getEmail()), ErrorConstants.EMAIL_ALREADY_USED);
        }
        optionalUser.get().changeBaseInfo(vm.getFullName(), vm.getEmail(), vm.getManager(), vm.getTitle(), vm.getMobileNumber(), vm.getEmployeeNumber(), vm.getSeq(), vm.getIcon());

    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        var optionalUser = userRepository.findById(id);
        Assert.notEmpty(optionalUser, ErrorConstants.USER_NOT_FOUND);
        userRepository.delete(optionalUser.get());
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String username, String password, String newPassword) {
        var optionalUser = userRepository.findOneByUserName(username);
        Assert.notEmpty(optionalUser, ErrorConstants.USER_NOT_FOUND);
        Assert.isTrue(passwordEncoder.matches(optionalUser.get().getPassword(), password), ErrorConstants.INVALID_PASSWORD);
        optionalUser.get().generatorPassword(passwordEncoder, newPassword);
    }


}
