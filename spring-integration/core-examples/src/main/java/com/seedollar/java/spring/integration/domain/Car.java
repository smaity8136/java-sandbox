package com.seedollar.java.spring.integration.domain;

/**
 * Created by seedollar on 9/27/16.
 */
public class Car implements Vehicle {

    private String brand;

    public Car(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String engineSpec() {
        return "v8 HEMI";
    }

    @Override
    public Float topSpeed() {
        return 240.00f;
    }

    @Override
    public Float weight() {
        return 1900.00f;
    }
}
