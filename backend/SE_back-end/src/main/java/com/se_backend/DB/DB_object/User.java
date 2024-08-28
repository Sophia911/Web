package com.se_backend.DB.DB_object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "avatar")
    private String avatar;
    @JsonProperty(value = "background")
    private String background;

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
