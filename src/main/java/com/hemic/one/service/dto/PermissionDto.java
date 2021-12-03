package com.hemic.one.service.dto;

import com.hemic.common.model.AbstractBaseDto;

/**
 * @Author jor
 * @create 2021/12/2 9:59
 */
public class PermissionDto extends AbstractBaseDto {

    private String authorizationUrl;

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }
}
