package com.hemic.one.web.rest.vm;

import com.hemic.one.service.dto.UserDto;
import java.io.Serial;

/**
 * @Author jor
 * @create 2021/11/9 15:03
 * <p>
 * A DTO representing a user . 用户对象的数据传输类
 * </p>
 */
public class UserVm extends UserDto {

    @Serial
    private static final long serialVersionUID = -4742448937348749246L;


    private String password;

    public UserVm() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
