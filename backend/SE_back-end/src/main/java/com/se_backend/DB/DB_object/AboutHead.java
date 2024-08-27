package com.se_backend.DB.DB_object;
import com.fasterxml.jackson.annotation.JsonProperty;
public class AboutHead{
    @JsonProperty(value = "aboutPageId")
    int aboutPageId;
    @JsonProperty(value = "backgroundImg")
    String backgroundImg;
    @JsonProperty(value = "title")
    String title;
    @JsonProperty(value = "description")
    String description;
    public AboutHead(){};
    public AboutHead(int aboutPageId,String title,String description,String background){
        this.aboutPageId=aboutPageId;
        this.title=title;
        this.description=description;
        this.backgroundImg=background;
    }
    public int get_Id() {
        return aboutPageId;
    }

    public void set_Id(int aboutPageId) {
        this.aboutPageId = aboutPageId;
    }

    public String get_title() {
        return title;
    }

    public void set_title(String title) {
        this.title = title;
    }

    public String get_description() {
        return description;
    }

    public void set_description(String description) {
        this.description = description;
    }

    public String get_background() {
        return backgroundImg;
    }

    public void set_background(String background) {
        this.backgroundImg = background;
    }
}