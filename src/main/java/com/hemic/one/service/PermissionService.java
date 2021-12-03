package com.hemic.one.service;

import com.hemic.common.utils.Assert;
import com.hemic.one.constants.ErrorConstants;
import com.hemic.one.domain.Permission;
import com.hemic.one.repository.PermissionRepository;
import com.hemic.one.web.rest.vm.PermissionVm;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author jor
 * @create 2021/12/2 9:57
 */
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public String create(PermissionVm vm) {
        Assert.isEmpty(permissionRepository.findByAuthorizationUrl(vm.getAuthorizationUrl()), ErrorConstants.PERMISSION_PATH_ALREADY_USED);
        Permission permission = new Permission(vm.getAuthorizationUrl());
        return permissionRepository.save(permission).getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(String id, PermissionVm vm) {
        Optional<Permission> optional = permissionRepository.findById(id);
        Assert.notEmpty(optional, ErrorConstants.PERMISSION_NOT_FOUND);
        Permission permission = optional.get();
        if (!permission.getAuthorizationUrl().equals(vm.getAuthorizationUrl())) {
            Assert.isEmpty(permissionRepository.findByAuthorizationUrl(vm.getAuthorizationUrl()), ErrorConstants.PERMISSION_PATH_ALREADY_USED);
            permission.setAuthorizationUrl(vm.getAuthorizationUrl());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        Optional<Permission> optional = permissionRepository.findById(id);
        Assert.notEmpty(optional, ErrorConstants.PERMISSION_NOT_FOUND);
        permissionRepository.delete(optional.get());

    }

}
