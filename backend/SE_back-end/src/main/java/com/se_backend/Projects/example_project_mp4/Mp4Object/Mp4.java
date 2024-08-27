package com.se_backend.Projects.example_project_mp4.Mp4Object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mp4 {
    @JsonProperty(value = "id")
    int id_Mp4;
    @JsonProperty(value = "name")
    String name;
    @JsonProperty(value = "path")
    String path;
    public Mp4(){};
    public Mp4(int id_images,String name,String path){
        this.id_Mp4 =id_images;
        this.name=name;
        this.path=path;
    }
    public int getId_Mp4() {
        return id_Mp4;
    }

    public void setId_Mp4(int id_Mp4) {
        this.id_Mp4 = id_Mp4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
