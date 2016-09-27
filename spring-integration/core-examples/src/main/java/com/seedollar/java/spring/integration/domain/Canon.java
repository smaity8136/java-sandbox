package com.seedollar.java.spring.integration.domain;

/**
 * Created by seedollar on 9/27/16.
 */
public class Canon {

    private int distance;

    public Canon(int distance) {
        this.distance = distance;
    }

    public String fire() {
        return "Boom!!! at a distance of: " + distance + " metres";
    }
}
