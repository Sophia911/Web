package com.se_backend.Projects.Main_interface_project.Main_interface_Object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainInterface {
    @JsonProperty("id")
    String id;
    @JsonProperty("url")
    String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
