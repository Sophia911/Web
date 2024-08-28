package com.se_backend.Projects.Backstage.Object.BackstageUserInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class BackstageUserInfoResult {

    private int total;

    @JsonProperty(value = "items")
    private ArrayList<BackstageUserInfo> user;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public BackstageUserInfoResult(int total, ArrayList<BackstageUserInfo> user) {
        this.total = total;
        this.user = user;
    }

    public ArrayList<BackstageUserInfo> getUser() {
        return user;
    }

    public void setUser(ArrayList<BackstageUserInfo> user) {
        this.user = user;
    }
}
