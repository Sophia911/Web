package com.se_backend.Projects.ContentManagement.ManagementObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.se_backend.Interceptor.UserPowerCheck;

public class GetUserRole {
    @JsonProperty("UserPowerCheck")
    UserPowerCheck userPowerCheck;

    public UserPowerCheck getUserPowerCheck() {
        return userPowerCheck;
    }

    public void setUserPowerCheck(UserPowerCheck userPowerCheck) {
        this.userPowerCheck = userPowerCheck;
    }
}
