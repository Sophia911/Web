package com.se_backend.Projects.example_project_mp3.Mp3Object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mp3 {
    @JsonProperty(value = "id")
    int id_Mp3;
    @JsonProperty(value = "name")
    String name;
    @JsonProperty(value = "path")
    String path;
    public Mp3(){};
    public Mp3(int id_images,String name,String path){
        this.id_Mp3 =id_images;
        this.name=name;
        this.path=path;
    }
    public int getId_Mp3() {
        return id_Mp3;
    }

    public void setId_Mp3(int id_Mp3) {
        this.id_Mp3 = id_Mp3;
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
