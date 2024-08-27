package com.se_backend.Projects.Backstage.Object.BackstageUserInfo;

public class BackstageUserInfo {
    private String email;
    private String role;

    public BackstageUserInfo(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
