package com.hemic.one.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import java.util.Locale;
import java.util.UUID;
import org.springframework.context.MessageSource;

/**
 * @Author jor
 * @create 2021/10/13 9:23
 */
public class ContextUtil {

    private final static TransmittableThreadLocal<ContextInfo> CONTEXT = new TransmittableThreadLocal<>();

    public static void remove() {
        CONTEXT.remove();
    }

   /* public static UserInfo getUserInfo() {
        return CONTEXT.get().userInfo();
    }*/

    public static String getTraceId() {
        return CONTEXT.get().traceId();
    }

    public static ContextInfo getContext(){
        return CONTEXT.get();
    }

    public static void setContext(String url,String jwt, Locale locale, MessageSource messageSource){
        CONTEXT.set(new ContextInfo(UUID.randomUUID().toString(), url, jwt,locale,messageSource));
    }



}
