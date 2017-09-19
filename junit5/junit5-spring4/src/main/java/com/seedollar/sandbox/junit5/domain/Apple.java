package com.seedollar.sandbox.junit5.domain;

public class Apple implements Snack, Fruit {

    @Override
    public String tasteComment() {
        return "Crunchy Sweet";
    }

    @Override
    public Color color() {
        return Color.RED;
    }

    @Override
    public String itemId() {
        return "F001";
    }

    @Override
    public Integer daysToExpiry() {
        return 4;
    }
}
