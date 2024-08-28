package com.se_backend.Projects.ContentManagement.ManagementObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.se_backend.Interceptor.UserPowerCheck;

public class GetVerificationCode {
    @JsonProperty("UserPowerCheck")
    UserPowerCheck userPowerCheck;
    @JsonProperty("mail")
    String mail;
    public UserPowerCheck getUserPowerCheck() {
        return userPowerCheck;
    }

    public void setUserPowerCheck(UserPowerCheck userPowerCheck) {
        this.userPowerCheck = userPowerCheck;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "getVerificationCode{" +
                "userPowerCheck=" + userPowerCheck +
                ", mail='" + mail + '\'' +
                '}';
    }
}
