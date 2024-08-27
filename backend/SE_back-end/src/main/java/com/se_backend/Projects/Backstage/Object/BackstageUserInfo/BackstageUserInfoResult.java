package com.se_backend.Projects.Backstage.Object.BackstageUserInfo;

import com.se_backend.DB.DB_object.UserRole;

import java.util.ArrayList;

public class BackstageUserInfoResult {

    private int total;

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
