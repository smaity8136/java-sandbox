package com.seedollar.springboot.actuator.custom.domain;

/**
 * Created by seedollar on 2/16/17.
 */
public class LocaleInfo {

    private String country;

    private String currentTime;

    public LocaleInfo(String country, String currentTime) {
        this.country = country;
        this.currentTime = currentTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
