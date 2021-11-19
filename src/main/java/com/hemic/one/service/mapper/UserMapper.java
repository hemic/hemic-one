package com.hemic.one.service.mapper;


import com.hemic.one.domain.User;
import com.hemic.one.service.dto.UserToken;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * @Author jor
 * @create 2021/11/9 15:04
 */


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserToken domainToDto(User user);

    List<UserToken> domainToDto(List<User> list);


}
