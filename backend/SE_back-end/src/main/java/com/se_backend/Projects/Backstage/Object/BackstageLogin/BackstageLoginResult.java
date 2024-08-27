package com.se_backend.Projects.Backstage.Object.BackstageLogin;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BackstageLoginResult {
    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "token")
    private String token;

    public BackstageLoginResult(String code, String token) {
        this.code = code;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
