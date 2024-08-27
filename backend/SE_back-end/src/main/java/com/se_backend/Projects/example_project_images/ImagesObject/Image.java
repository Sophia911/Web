package com.se_backend.Projects.example_project_images.ImagesObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    @JsonProperty(value = "id")
    int id_images;
    @JsonProperty(value = "name")
    String name;
    @JsonProperty(value = "path")
    String path;
    public Image(){};
    public Image(int id_images,String name,String path){
        this.id_images=id_images;
        this.name=name;
        this.path=path;
    }
    public int getId_images() {
        return id_images;
    }

    public void setId_images(int id_images) {
        this.id_images = id_images;
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
