package com.hemic.one.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author jor
 * @create 2021/11/19 11:26
 */
public class JWTToken {

    private String idToken;

    public JWTToken(String idToken) {
        this.idToken = idToken;
    }

    @JsonProperty("id_token")
    String getIdToken() {
        return idToken;
    }

    void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
