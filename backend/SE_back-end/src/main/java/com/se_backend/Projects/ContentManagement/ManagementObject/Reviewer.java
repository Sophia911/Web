package com.se_backend.Projects.ContentManagement.ManagementObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.se_backend.Interceptor.UserPowerCheck;

public class Reviewer {
    @JsonProperty("UserPowerCheck")
    UserPowerCheck userPowerCheck;

    @JsonProperty("Reviewer")
    String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UserPowerCheck getUserPowerCheck() {
        return userPowerCheck;
    }

    public void setUserPowerCheck(UserPowerCheck userPowerCheck) {
        this.userPowerCheck = userPowerCheck;
    }
}
