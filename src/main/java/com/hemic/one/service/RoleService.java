package com.hemic.one.service;

import com.hemic.common.utils.Assert;
import com.hemic.common.utils.LinkListSpilt;
import com.hemic.one.constants.ErrorConstants;
import com.hemic.one.domain.Permission;
import com.hemic.one.domain.Role;
import com.hemic.one.repository.PermissionRepository;
import com.hemic.one.repository.RoleRepository;
import com.hemic.one.service.dto.PermissionDto;
import com.hemic.one.service.dto.RoleDto;
import com.hemic.one.service.mapper.PermissionMapper;
import com.hemic.one.service.mapper.RoleMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jor
 */
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper, PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public String create(RoleDto dto) {
        Assert.isEmpty(roleRepository.findOneByName(dto.getName()), ErrorConstants.ROLE_NAME_ALREADY_USED);
        Role role = new Role(dto.getName());
        return roleRepository.save(role).getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(String id, RoleDto dto) {
        Optional<Role> optional = roleRepository.findById(id);
        Assert.notEmpty(optional, ErrorConstants.ROLE_NOT_FOUND);
        Role role = optional.get();
        if (!role.getName().equals(dto.getName())) {
            Assert.isEmpty(roleRepository.findOneByName(dto.getName()), ErrorConstants.ROLE_NAME_ALREADY_USED);
            role.setName(dto.getName());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        Optional<Role> optional = roleRepository.findById(id);
        Assert.notEmpty(optional, ErrorConstants.ROLE_NOT_FOUND);
        roleRepository.delete(optional.get());
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePermission(String id, List<String> ids) {
        Optional<Role> optional = roleRepository.findById(id);
        Assert.notEmpty(optional, ErrorConstants.ROLE_NOT_FOUND);
        Role role = optional.get();
        List<Permission> permissions = permissionRepository.findAllById(ids);
        LinkListSpilt<Permission> spilt = new LinkListSpilt<>(role.getPermissions(), permissions);
        spilt.getCreateList().forEach(role::addPermission);
        spilt.getRemoveIds().forEach(role::removePermission);
    }

    @Transactional(readOnly = true)
    public List<PermissionDto> getPermissionByRoleId(String id) {
        Optional<Role> optional = roleRepository.findById(id);
        Assert.notEmpty(optional, ErrorConstants.ROLE_NOT_FOUND);
        Role role = optional.get();
        return permissionMapper.domainToDto(role.getPermissions());
    }


}
