package com.se_backend.Projects.video.VideoObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Video {
    int id;
    @JsonProperty(value = "owner_id")
    String owner_id;
    @JsonProperty(value = "name")
    String name;
    @JsonProperty(value = "url")
    String url;
    @JsonProperty(value = "time")
    String time;
    @JsonProperty(value = "type")
    String type;
    @JsonProperty(value = "pic_id")
    String pic_id;
    public Video(String owner_id,String name, String url,String type, String pic_id){
        this.owner_id=owner_id;
        this.name=name;
        this.url=url;
        this.type=type;
        this.pic_id=pic_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic_id() {
        return pic_id;
    }

    public void setPic_id(String pic_id) {
        this.pic_id = pic_id;
    }
}
