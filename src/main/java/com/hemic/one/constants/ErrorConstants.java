package com.hemic.one.constants;

/**
 * @Author jor
 * @create 2021/10/25 17:23
 */
public enum ErrorConstants implements BaseError{
    LOGIN_ALREADY_USED(5001,"error.com.hemic.one.service.UserService.LoginAlreadyUsed"),
    EMAIL_ALREADY_USED(5002,"error.com.hemic.one.service.UserService.EmailAlreadyUsed")

    ;


    ErrorConstants(int code, String message){
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
