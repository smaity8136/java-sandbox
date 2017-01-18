package com.seedollar.java.sandbox.javaslang.unapply.model;

/**
 * Created by seedollar on 1/18/17.
 */
public class Dog {

    private String name;

    private String breed;

    public Dog(String name, String breed) {

        this.name = name;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
