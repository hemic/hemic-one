package com.hemic.one.constants;

/**
 * @Author jor
 * @create 2021/10/25 17:23
 */
public enum ErrorConstants implements BaseError{
    ;



    ErrorConstants(int code, String detail){
        this.code = code;
        this.detail = detail;
    }

    private final int code;
    public final String detail;
    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.detail;
    }
}
