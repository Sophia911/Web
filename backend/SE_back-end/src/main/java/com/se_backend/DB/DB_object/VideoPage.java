package com.se_backend.DB.DB_object;

public class VideoPage {
    Integer id;
    String backgroundImg;
    String videoBackGroundImg;
    String videoRecommendImg;
    String carouselImg_1;
    String carouselImg_2;
    String carouselImg_3;
    String carouselImg_4;
    String carouselImg_5;
    public VideoPage(String backgroundImg, String videoRecommendImg, String videoBackGroundImg,
    String carouselImg_1, String carouselImg_2, String carouselImg_3, String carouselImg_4, String carouselImg_5){
        this.backgroundImg = backgroundImg;
        this.videoBackGroundImg = videoBackGroundImg;
        this.videoRecommendImg = videoRecommendImg;
        this.carouselImg_1 = carouselImg_1;
        this.carouselImg_2 = carouselImg_2;
        this.carouselImg_3 = carouselImg_3;
        this.carouselImg_4 = carouselImg_4;
        this.carouselImg_5 = carouselImg_5;
    }
    public VideoPage(){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public String getVideoBackGroundImg() {
        return videoBackGroundImg;
    }

    public void setVideoBackGroundImg(String videoBackGroundImg) {
        this.videoBackGroundImg = videoBackGroundImg;
    }

    public String getVideoRecommendImg() {
        return videoRecommendImg;
    }

    public void setVideoRecommendImg(String videoRecommendImg) {
        this.videoRecommendImg = videoRecommendImg;
    }

    public String getCarouselImg_1() {
        return carouselImg_1;
    }

    public void setCarouselImg_1(String carouselImg_1) {
        this.carouselImg_1 = carouselImg_1;
    }

    public String getCarouselImg_2() {
        return carouselImg_2;
    }

    public void setCarouselImg_2(String carouselImg_2) {
        this.carouselImg_2 = carouselImg_2;
    }

    public String getCarouselImg_3() {
        return carouselImg_3;
    }

    public void setCarouselImg_3(String carouselImg_3) {
        this.carouselImg_3 = carouselImg_3;
    }

    public String getCarouselImg_4() {
        return carouselImg_4;
    }

    public void setCarouselImg_4(String carouselImg_4) {
        this.carouselImg_4 = carouselImg_4;
    }

    public String getCarouselImg_5() {
        return carouselImg_5;
    }

    public void setCarouselImg_5(String carouselImg_5) {
        this.carouselImg_5 = carouselImg_5;
    }
}
