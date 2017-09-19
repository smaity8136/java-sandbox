package com.seedollar.sandbox.junit5.domain;

public class SodaPop implements Snack {
    @Override
    public String tasteComment() {
        return "Sweet, Cold";
    }

    @Override
    public Color color() {
        return Color.ORANGE;
    }

    @Override
    public String itemId() {
        return "S003";
    }
}
