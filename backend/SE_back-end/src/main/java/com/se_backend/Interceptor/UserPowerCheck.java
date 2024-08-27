package com.se_backend.Interceptor;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class UserPowerCheck {
    @JsonProperty("id")
     String id;
    @JsonProperty("token")
     String token;
    @JsonProperty("powers")
     ArrayList<String> powers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<String> getPowers() {
        return powers;
    }

    public void setPowers(ArrayList<String> powers) {
        this.powers = powers;
    }
}
