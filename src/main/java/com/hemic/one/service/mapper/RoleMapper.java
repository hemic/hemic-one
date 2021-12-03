package com.hemic.one.service.mapper;

import com.hemic.one.domain.Role;
import com.hemic.one.service.dto.RoleDto;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto domainToDto(Role role);

    List<RoleDto> domainToDto(Collection<Role> role);
}
