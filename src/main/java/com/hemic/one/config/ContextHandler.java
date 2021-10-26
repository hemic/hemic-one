package com.hemic.one.config;

import com.hemic.one.security.jwt.TokenProvider;
import com.hemic.one.utils.ContextUtil;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @Author jor
 * @create 2021/10/14 9:12
 */
public class ContextHandler implements HandlerInterceptor {

    private final static String langKey="langKey";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;



    private final MessageSource messageSource;

    public ContextHandler(TokenProvider tokenProvider, MessageSource messageSource) {
        this.tokenProvider = tokenProvider;
        this.messageSource = messageSource;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwt = resolveToken(request);
        ContextUtil.setContext(request.getRequestURI(),jwt, LocaleContextHolder.getLocale(),messageSource);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        ContextUtil.remove();
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
