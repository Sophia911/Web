package com.se_backend.DB.DB_object;

public class Webpage {
    Integer id;
    Integer themeID;
    Integer VideoPageID;
    Integer AboutPageID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThemeID() {
        return themeID;
    }

    public void setThemeID(Integer themeID) {
        this.themeID = themeID;
    }

    public Integer getVideoPageID() {
        return VideoPageID;
    }

    public void setVideoPageID(Integer videoPageID) {
        VideoPageID = videoPageID;
    }

    public Integer getAboutPageID() {
        return AboutPageID;
    }

    public void setAboutPageID(Integer aboutPageID) {
        AboutPageID = aboutPageID;
    }
}
