package com.seedollar.sandbox.junit5.domain;

public class Pear implements Snack, Fruit {
    @Override
    public String tasteComment() {
        return "Soft, Sweet";
    }

    @Override
    public Color color() {
        return Color.GREEN;
    }

    @Override
    public String itemId() {
        return "F003";
    }

    @Override
    public Integer daysToExpiry() {
        return 8;
    }
}
