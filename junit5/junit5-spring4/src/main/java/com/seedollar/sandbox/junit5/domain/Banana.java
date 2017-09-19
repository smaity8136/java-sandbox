package com.seedollar.sandbox.junit5.domain;

public class Banana implements Snack, Fruit {
    @Override
    public String tasteComment() {
        return "Mushy";
    }

    @Override
    public Color color() {
        return Color.YELLOW;
    }

    @Override
    public String itemId() {
        return "F002";
    }

    @Override
    public Integer daysToExpiry() {
        return 6;
    }
}
