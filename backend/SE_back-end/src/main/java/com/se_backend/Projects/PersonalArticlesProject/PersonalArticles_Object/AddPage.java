package com.se_backend.Projects.PersonalArticlesProject.PersonalArticles_Object;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.se_backend.DB.DB_object.PersonalArticles;
import com.se_backend.Interceptor.UserPowerCheck;

public class AddPage {
    @JsonProperty("UserPowerCheck")
    UserPowerCheck userPowerCheck;
    @JsonProperty("personalArticle")
    PersonalArticles personalArticle;
    public UserPowerCheck getUserPowerCheck() {
        return userPowerCheck;
    }

    public void setUserPowerCheck(UserPowerCheck userPowerCheck) {
        this.userPowerCheck = userPowerCheck;
    }

    public PersonalArticles getPersonalArticle() {
        return personalArticle;
    }

    public void setPersonalArticle(PersonalArticles personalArticle) {
        this.personalArticle = personalArticle;
    }
}
