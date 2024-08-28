package com.se_backend.Projects.ContentManagement.ManagementObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.se_backend.DB.DB_object.UserVerificationCode;
import com.se_backend.Interceptor.UserPowerCheck;

public class CheckVerificationCode {
    @JsonProperty("UserPowerCheck")
    UserPowerCheck userPowerCheck;
    @JsonProperty("UserVerificationCode")
    UserVerificationCode userVerificationCode;

    public UserPowerCheck getUserPowerCheck() {
        return userPowerCheck;
    }

    public void setUserPowerCheck(UserPowerCheck userPowerCheck) {
        this.userPowerCheck = userPowerCheck;
    }

    public UserVerificationCode getUserVerificationCode() {
        return userVerificationCode;
    }

    public void setUserVerificationCode(UserVerificationCode userVerificationCode) {
        this.userVerificationCode = userVerificationCode;
    }
}
