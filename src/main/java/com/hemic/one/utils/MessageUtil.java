package com.hemic.one.utils;

/**
 * @Author jor
 * @create 2021/10/26 14:42
 */
public class MessageUtil {

    public static String get(String msgKey) {
        try {
            return  ContextUtil.getContext().messageSource().getMessage(msgKey, null, ContextUtil.getContext().locale());
        } catch (Exception e) {
            return msgKey;
        }
    }
}
