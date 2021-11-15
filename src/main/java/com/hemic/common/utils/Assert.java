package com.hemic.common.utils;


import com.hemic.common.model.BaseError;
import java.util.Collection;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @Author jor
 * @create 2021/10/26 15:15
 */
public class Assert {

    public static void isTrue(boolean expression, BaseError error) {
        if (!expression) {
            throw error.getException();
        }
    }

    public static void isFalse(boolean expression, BaseError error) {
        if (expression) {
            throw error.getException();
        }
    }

    public static void isEmpty(Collection array, BaseError error) {
        if (!CollectionUtils.isEmpty(array)) {
            throw error.getException();
        }
    }

    public static void notEmpty(Collection array, BaseError error) {
        if (CollectionUtils.isEmpty(array)) {
            throw error.getException();
        }
    }

    public static void isEmpty(String str, BaseError error) {
        if (!StringUtils.isEmpty(str)) {
            throw error.getException();
        }
    }

    public static void notEmpty(String str, BaseError error) {
        if (StringUtils.isEmpty(str)) {
            throw error.getException();
        }
    }

    public static void notNull(Object obj, BaseError error) {
        if (obj == null) {
            throw error.getException();
        }
    }

    public static void isNull(Object obj, BaseError error) {
        if (obj != null) {
            throw error.getException();
        }
    }

    public static void notEmpty(Optional optional, BaseError error) {
        if (optional.isEmpty()) {
            throw error.getException();
        }
    }

    public static void isEmpty(Optional optional, BaseError error) {
        if (optional.isPresent()) {
            throw error.getException();
        }
    }

}
