package com.se_backend.Projects.Backstage.Object.BackstageLogin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BackstageLoginRequest {
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "password")
    private String passwd;

    @Override
    public String toString() {
        return "BackstageLoginRequest{" +
                "username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
