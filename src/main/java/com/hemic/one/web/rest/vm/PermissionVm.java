package com.hemic.one.web.rest.vm;

import java.io.Serializable;

/**
 * @Author jor
 * @create 2021/12/3 10:44
 */
public class PermissionVm implements Serializable {

    private String authorizationUrl;

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }
}
