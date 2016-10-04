package com.seedollar.java.spring.integration.domain;

/**
 * Created by seedollar on 10/4/16.
 */
public class Butterfly {

    private String colour;
    private int wingSpan;

    public Butterfly(String colour, int wingSpan) {
        this.colour = colour;
        this.wingSpan = wingSpan;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
    }

    @Override
    public String toString() {
        StringBuilder butterflyString = new StringBuilder();
        butterflyString.append("\nButterfly");
        butterflyString.append("\nColour: ").append(getColour());
        butterflyString.append("\nWing Span: ").append(getWingSpan());
        return butterflyString.toString();
    }
}
