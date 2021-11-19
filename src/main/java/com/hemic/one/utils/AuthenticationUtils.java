package com.hemic.one.utils;

import com.hemic.common.model.BaseError;
import com.hemic.one.security.AuthenticationException;


/**
 * @Author jor
 * @create 2021/11/9 11:33
 */
public class AuthenticationUtils {


    public static void isUsernameValid(Boolean expression, BaseError error) {
        if (!expression) {
            throw new AuthenticationException(error);
        }
    }


}
