package com.se_backend.DB.DB_object;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ModuleInfo{

    @JsonProperty(value = "image1")
    String img1;
    @JsonProperty(value = "image2")
    String img2;
    @JsonProperty(value = "image3")
    String img3;
    @JsonProperty(value = "introduce")
    String introduce;
    public ModuleInfo(){};
    public ModuleInfo(String img1,String img2,String img3, String introduce){
        this.img1=img1;
        this.img2=img2;
        this.img3=img3;
        this.introduce=introduce;
    }

    public String getImage1() {
        return img1;
    }

    public String getImage2() {
        return img2;
    }
    public String getImage3() {
        return img3;
    }

    public String getIntroduce() {
        return introduce;
    }

}