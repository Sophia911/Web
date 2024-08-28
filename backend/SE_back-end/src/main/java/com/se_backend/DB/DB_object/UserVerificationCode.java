package com.se_backend.DB.DB_object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserVerificationCode {
    @JsonProperty("mail")
    String mail;
    @JsonProperty("Code")
    Integer Code;
    String time;
    public UserVerificationCode(String mail,Integer Code) {
        this.mail = mail;
        this.Code = Code;
    }
    public UserVerificationCode(){}
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
