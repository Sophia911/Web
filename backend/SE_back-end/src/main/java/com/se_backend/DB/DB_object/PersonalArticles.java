package com.se_backend.DB.DB_object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalArticles {
    @JsonProperty("title")
    String title;
    @JsonProperty("content")
    String content;
    @JsonProperty("time")
    String time;
    @JsonProperty("author")
    String author;
    @JsonProperty("id")
    Integer id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
