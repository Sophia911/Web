package com.se_backend.Projects.User;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserObject {
    @JsonProperty(value = "email")
    private String id;
    @JsonProperty(value = "password")
    private String password;
    @JsonProperty(value = "avatar")
    private String avatar;
    @JsonProperty(value = "background")
    private String background;
    private String name;
    private int introduction;

    public UserObject(String id) {
        this.id = id;
    }
    public UserObject(){}
    public void setIntroduction(int introduction) {
        this.introduction = introduction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
