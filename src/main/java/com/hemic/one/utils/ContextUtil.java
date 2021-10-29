package com.hemic.one.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import java.util.Locale;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @Author jor
 * @create 2021/10/13 9:23
 */
public class ContextUtil {

    private final static TransmittableThreadLocal<ContextInfo> CONTEXT = new TransmittableThreadLocal<>();
    private final static MessageSource defaultMessageSource = defaultMessageSource();

    public static void remove() {
        CONTEXT.remove();
    }

   /* public static UserInfo getUserInfo() {
        return CONTEXT.get().userInfo();
    }*/

    public static String getTraceId() {
        initialize();
        return CONTEXT.get().traceId();
    }

    public static ContextInfo getContext() {
        initialize();
        return CONTEXT.get();
    }

    public static void setContext(String url, String jwt, Locale locale, MessageSource messageSource) {
        CONTEXT.set(new ContextInfo(UUID.randomUUID().toString(), url, jwt, locale, messageSource));
    }

    private static void initialize() {
        if (CONTEXT.get() == null) {
            CONTEXT.set(new ContextInfo(UUID.randomUUID().toString(), StringUtils.EMPTY, StringUtils.EMPTY, Locale.ENGLISH, defaultMessageSource));
        }
    }

    private static MessageSource defaultMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("exception");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }


}
