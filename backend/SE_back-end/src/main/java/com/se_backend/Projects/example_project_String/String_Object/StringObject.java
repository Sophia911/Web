package com.se_backend.Projects.example_project_String.String_Object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StringObject {
    @JsonProperty(value = "id")
    int id;
    @JsonProperty(value = "value")
    String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
