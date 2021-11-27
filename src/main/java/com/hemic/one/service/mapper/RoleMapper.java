package com.hemic.one.service.mapper;

import com.hemic.one.domain.Role;
import com.hemic.one.service.dto.RoleDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto domainToDto(Role role);

    List<Role> domainToDto(Collection<Role> role);
}
