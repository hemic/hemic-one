package com.hemic.one.constants;

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
    USER_NOT_FOUND_BY_RESET_KEY(5006, "error.com.hemic.one.service.UserService.UserNotFoundByResetKey"),
    ;


    ErrorConstants(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    public final String message;

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
