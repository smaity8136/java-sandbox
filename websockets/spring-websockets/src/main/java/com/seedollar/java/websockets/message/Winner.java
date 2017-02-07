package com.seedollar.java.websockets.message;

/**
 * Created by seedollar on 2/7/17.
 */
public class Winner {

    private String name;

    public Winner() {
    }

    public Winner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
