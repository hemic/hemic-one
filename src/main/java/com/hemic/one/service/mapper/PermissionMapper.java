package com.hemic.one.service.mapper;

import com.hemic.one.domain.Permission;
import com.hemic.one.service.dto.PermissionDto;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * @Author jor
 * @create 2021/12/3 10:40
 */
@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDto domainToDto(Permission permission);

    List<PermissionDto> domainToDto(Collection<Permission> permissions);
}
