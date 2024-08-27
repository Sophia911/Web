package com.se_backend.Projects.About_page_project;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.se_backend.DB.DB_object.ModuleInfo;

import java.util.ArrayList;

public class AboutInfo{

    @JsonProperty(value = "title")
    String title;
    @JsonProperty(value = "description")
    String describe;
    @JsonProperty(value = "background")
    String background;
    @JsonProperty(value = "show")
    ArrayList<ModuleInfo> show;
    public AboutInfo(){}
    public AboutInfo(String title,String describe, String background,ArrayList<ModuleInfo> show){
        this.title=title;
        this.describe=describe;
        this.background=background;
        this.show=show;

    }


    public void set_describe(String describe) {
        this.describe = describe;
    }
    public void set_background(String background) {
            this.background = background;
    }

    public void set_title(String title) {
            this.title = title;
    }
    public void set_showList(ArrayList<ModuleInfo> show){this.show=show;}
}