package com.se_backend.Projects.ContentManagement.ManagementObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.se_backend.Interceptor.UserPowerCheck;

public class VideoReviewed {
    @JsonProperty("UserPowerCheck")
    UserPowerCheck userPowerCheck;
    @JsonProperty("Reviewer")
    String user;
    @JsonProperty("VideoID")
    Integer id;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserPowerCheck getUserPowerCheck() {
        return userPowerCheck;
    }

    public void setUserPowerCheck(UserPowerCheck userPowerCheck) {
        this.userPowerCheck = userPowerCheck;
    }
}
