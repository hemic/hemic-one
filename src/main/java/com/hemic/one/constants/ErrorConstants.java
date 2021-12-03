package com.hemic.one.constants;

import com.hemic.common.error.ServiceException;
import com.hemic.common.error.ThrowableProblemWithCode;
import com.hemic.common.model.BaseError;

/**
 * @Author jor
 * @create 2021/10/25 17:23
 */
public enum ErrorConstants implements BaseError {
    LOGIN_ALREADY_USED(5001, "error.com.hemic.one.service.UserService.LoginAlreadyUsed"),
    EMAIL_ALREADY_USED(5002, "error.com.hemic.one.service.UserService.EmailAlreadyUsed"),
    INVALID_PASSWORD(5003, "error.com.hemic.one.service.UserService.InvalidPassword"),
    INVALID_ACTIVATION_KEY(5004, "error.com.hemic.one.service.UserService.InvalidActivationKey"),
    USER_NOT_FOUND(5005, "error.com.hemic.one.service.UserService.UserNotFound"),
    ROLE_NAME_ALREADY_USED(5006, "error.com.hemic.one.service.RoleService.RoleNameAlreadyUsed"),
    ROLE_NOT_FOUND(5007, "error.com.hemic.one.service.RoleService.RoleNotFound"),
    PERMISSION_PATH_ALREADY_USED(5008, "error.com.hemic.one.service.PermissionService.PathAlreadyUsed"),
    PERMISSION_NOT_FOUND(5009, "error.com.hemic.one.service.PermissionNotFound");


    ErrorConstants(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    public final String message;


    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public ThrowableProblemWithCode getException() {
        return new ServiceException(this);
    }
}
