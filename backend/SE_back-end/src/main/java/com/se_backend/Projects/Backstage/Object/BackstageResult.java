package com.se_backend.Projects.Backstage.Object;

public class BackstageResult {
    int code;
    String message;
    Object data;
    public BackstageResult(int code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
