package com.se_backend.DB.DB_object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BackstageUser {
    @JsonProperty(value = "email")
    private String id;
    @JsonProperty(value = "avatar")
    private String avatar;
    @JsonProperty(value = "background")
    private String background;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "introduction")
    private String introduction;

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getBackground() {
        return background;
    }
    public void printUserInfo(){
        System.out.println("email: " + this.id);
        System.out.println("name: " + this.name);
        System.out.println("avatar: " + this.avatar);
        System.out.println("introduction: " + this.introduction);
    }
}
