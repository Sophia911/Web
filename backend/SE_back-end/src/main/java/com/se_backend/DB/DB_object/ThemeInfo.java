package com.se_backend.DB.DB_object;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ThemeInfo {
    @JsonProperty(value = "theme_name")
    String theme_name;
    @JsonProperty(value = "theme_id")
    String theme_id;
    public String getThemeName() {
        return theme_name;
    }
    public String getThemeId() {
        return theme_id;
    }


}
