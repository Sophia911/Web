package com.se_backend.Projects.Backstage.Object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BackstagePowerChange {
    @JsonProperty(value = "add")
    private boolean add;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "power")
    private String power;
    @Override
    public String toString() {
        String operation = add ? "add" : "del";
        return operation + ' ' + power + ": " + email;
    }

    public boolean isAdd() {
        return add;
    }

    public String getEmail() {
        return email;
    }

    public String getPower() {
        return power;
    }
}
