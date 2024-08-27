package com.se_backend.Projects.User_project;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPage {
        @JsonProperty(value = "avatar")
        String avatar;
        @JsonProperty(value = "id")
        String id;
        @JsonProperty(value = "name")
        String name;
        @JsonProperty(value = "introduction")
        String introduction;
        @JsonProperty(value = "background")
        String background;
        @Override
        public String toString() {
        return "id:"+id+" name:"+name;
    }
        public UserPage(){}
        public UserPage(String id,String name,String avatar,String background,String introduction){

            this.id=id;
            this.name = name;
            this.avatar =avatar;
            this.background=background;
            this.introduction=introduction;
        }

        public String avatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

        public String getID() {
            return id;
        }

        public void setID(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String sex) {
            this.introduction = introduction;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
        this.background = background;
    }


}
