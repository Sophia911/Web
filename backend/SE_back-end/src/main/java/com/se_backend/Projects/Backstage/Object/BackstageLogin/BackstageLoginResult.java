package com.se_backend.Projects.Backstage.Object.BackstageLogin;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BackstageLoginResult {
    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "token")
    private String token;

    public BackstageLoginResult(int code, String token) {
        this.code = code;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
