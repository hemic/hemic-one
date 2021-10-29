package com.hemic.one.utils;

import java.util.Locale;
import org.springframework.context.MessageSource;

/**
 * @Author jor
 * @create 2021/10/13 9:29
 */
public record ContextInfo(String traceId, String url, String jwt, Locale locale, MessageSource messageSource) {

}
