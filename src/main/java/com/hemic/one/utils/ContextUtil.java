package com.hemic.one.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import java.util.UUID;

/**
 * @Author jor
 * @create 2021/10/13 9:23
 */
public class ContextUtil {

    private final static TransmittableThreadLocal<ContextInfo> context = new TransmittableThreadLocal();

    public static void remove() {
        context.remove();
    }

    public static UserInfo getUserInfo() {
        return context.get().userInfo();
    }

    public static String getTraceId() {
        return context.get().traceId();
    }

    public void setContext(String languageKey,String url,UserInfo userInfo){
     context.set(new ContextInfo(UUID.randomUUID().toString(), languageKey, url, userInfo));
    }



}
