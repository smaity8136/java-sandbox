package com.seedollar.java.spring.integration.domain;

/**
 * Created by seedollar on 9/27/16.
 */
public class Tank implements Vehicle {

    private String country;

    public Tank(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String engineSpec() {
        return "V12";
    }

    @Override
    public Float topSpeed() {
        return 80.0F;
    }

    @Override
    public Float weight() {
        return 5000.0F;
    }
}
