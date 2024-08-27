package com.se_backend.DB.DB_object;

public class UserToken {
    String id;
    String token;
    public UserToken(String id,String token){
        this.id = id;
        this.token = token;
    }
    public UserToken(){}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
