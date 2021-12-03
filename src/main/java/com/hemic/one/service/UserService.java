package com.hemic.one.service;

import com.hemic.common.utils.Assert;
import com.hemic.common.utils.LinkListSpilt;
import com.hemic.one.config.ApplicationProperties;
import com.hemic.one.constants.ErrorConstants;
import com.hemic.one.domain.Role;
import com.hemic.one.domain.User;
import com.hemic.one.repository.RoleRepository;
import com.hemic.one.repository.UserRepository;
import com.hemic.one.service.dto.RoleDto;
import com.hemic.one.service.dto.UserDto;
import com.hemic.one.service.dto.UserToken;
import com.hemic.one.service.mapper.RoleMapper;
import com.hemic.one.service.mapper.UserMapper;
import java.util.List;
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


    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ApplicationProperties applicationProperties, UserMapper userMapper,
        RoleRepository roleRepository, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationProperties = applicationProperties;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public String create(UserDto dto) {
        Assert.isEmpty(userRepository.findOneByUsername(dto.getUsername()), ErrorConstants.LOGIN_ALREADY_USED);
        Assert.isEmpty(userRepository.findOneByEmailIgnoreCase(dto.getEmail()), ErrorConstants.EMAIL_ALREADY_USED);
        User user = new User(dto.getUsername(), dto.getFullName(), dto.getEmail(), dto.getManager(), dto.getTitle(), dto.getMobileNumber(), dto.getEmployeeNumber(), dto.getSeq(),
            dto.getIcon());
        user.generatorPassword(passwordEncoder, applicationProperties.getDefaultPassword());
        return userRepository.save(user).getId();
    }


    @Transactional(rollbackFor = Exception.class)
    public void update(String id, UserDto dto) {
        var optionalUser = userRepository.findById(id);
        Assert.notEmpty(optionalUser, ErrorConstants.USER_NOT_FOUND);
        if (!dto.getEmail().equals(optionalUser.get().getEmail())) {
            Assert.isEmpty(userRepository.findOneByEmailIgnoreCase(dto.getEmail()), ErrorConstants.EMAIL_ALREADY_USED);
        }
        optionalUser.get().changeBaseInfo(dto.getFullName(), dto.getEmail(), dto.getManager(), dto.getTitle(), dto.getMobileNumber(), dto.getEmployeeNumber(), dto.getSeq(), dto.getIcon());

    }


    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        var optionalUser = userRepository.findById(id);
        Assert.notEmpty(optionalUser, ErrorConstants.USER_NOT_FOUND);
        userRepository.delete(optionalUser.get());
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String id, String password, String newPassword) {
        var optionalUser = userRepository.findById(id);
        Assert.notEmpty(optionalUser, ErrorConstants.USER_NOT_FOUND);
        Assert.isTrue(passwordEncoder.matches(optionalUser.get().getPassword(), password), ErrorConstants.INVALID_PASSWORD);
        optionalUser.get().generatorPassword(passwordEncoder, newPassword);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changeUserRole(String id, List<String> roleIds) {
        var optionalUser = userRepository.findById(id);
        Assert.notEmpty(optionalUser, ErrorConstants.USER_NOT_FOUND);
        List<Role> roles = roleRepository.findAllById(roleIds);
        User user = optionalUser.get();
        LinkListSpilt<Role> spilt = new LinkListSpilt<>(user.getRoles(), roles);
        spilt.getCreateList().forEach(user::addRole);
        spilt.getRemoveIds().forEach(user::removeRole);

    }

    @Transactional(readOnly = true)
    public List<RoleDto> getRoleByUserId(String id) {
        var optionalUser = userRepository.findById(id);
        Assert.notEmpty(optionalUser, ErrorConstants.USER_NOT_FOUND);
        return roleMapper.domainToDto(optionalUser.get().getRoles());
    }


    public UserToken getByUsername(String username) {
        var optionalUser = userRepository.findOneByUsername(username);
        return userMapper.domainToDto(optionalUser.orElse(null));
    }


}
