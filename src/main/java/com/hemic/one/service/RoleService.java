package com.hemic.one.service;

import com.hemic.common.utils.Assert;
import com.hemic.one.constants.ErrorConstants;
import com.hemic.one.domain.Role;
import com.hemic.one.repository.RoleRepository;
import com.hemic.one.service.dto.RoleDto;
import com.hemic.one.service.mapper.RoleMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class RoleService {
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper){
        this.roleMapper=roleMapper;
        this.roleRepository=roleRepository;
    }

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Transactional(rollbackFor = Exception.class)
    public String create(RoleDto dto){
        Assert.isEmpty(roleRepository.findOneByName(dto.getName()), ErrorConstants.ROLE_NAME_ALREADY_USED);
        Role role=new Role(dto.getName());
        return roleRepository.save(role).getId();
    }
    @Transactional(rollbackFor = Exception.class)
    public void update(String id,RoleDto dto){
        Optional<Role> optional=roleRepository.findById(id);
        Assert.notEmpty(optional,ErrorConstants.ROLE_NOT_FOUND);
        Role role=optional.get();
        if(!role.getName().equals(dto.getName())){
            role.setName(dto.getName());
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id){
        Optional<Role> optional=roleRepository.findById(id);
        Assert.notEmpty(optional,ErrorConstants.ROLE_NOT_FOUND);
        roleRepository.delete(optional.get());
    }


}
