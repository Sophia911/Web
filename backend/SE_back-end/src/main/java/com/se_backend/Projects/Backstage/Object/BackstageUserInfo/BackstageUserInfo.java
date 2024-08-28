package com.se_backend.Projects.Backstage.Object.BackstageUserInfo;

import java.util.ArrayList;

public class BackstageUserInfo {
    private String email;
    private ArrayList<String> roles;
    private String name;
    private String avatar;
    private String introduction;

    public BackstageUserInfo(String email, ArrayList<String> role) {
        this.email = email;
        this.roles = role;
    }
    public BackstageUserInfo(String email, ArrayList<String> role, String name, String avatar, String introduction) {
        this.email = email;
        this.roles = role;
        this.name = name;
        this.avatar = avatar;
        this.introduction = introduction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> role) {
        this.roles = role;
    }

    public void addRole(String role){
        this.roles.add(role);
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void printUserInfo(){
        System.out.println("name: " + this.name);
        System.out.println("avatar: " + this.avatar);
        System.out.println("background: " + this.introduction);
        System.out.println("roles: " + this.roles);
    }
}
