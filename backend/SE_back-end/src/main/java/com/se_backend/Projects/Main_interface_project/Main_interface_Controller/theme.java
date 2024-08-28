package com.se_backend.Projects.Main_interface_project.Main_interface_Controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class theme {
    @JsonProperty("Theme")
    String Theme;

    public String getTheme() {
        return Theme;
    }

    public void setTheme(String theme) {
        Theme = theme;
    }
}
