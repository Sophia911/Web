package com.se_backend.DB.DB_object;

public class URLPowers {
    int id;
    String URL;
    String Func;
    int PowerNeed;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getFunc() {
        return Func;
    }

    public void setFunc(String func) {
        Func = func;
    }

    public int getPowerNeed() {
        return PowerNeed;
    }

    public void setPowerNeed(int powerNeed) {
        PowerNeed = powerNeed;
    }
}
