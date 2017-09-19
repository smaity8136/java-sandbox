package com.seedollar.sandbox.junit5.domain;

public class PotatoChips implements Snack {


    @Override
    public String tasteComment() {
        return "Crunchy";
    }

    @Override
    public Color color() {
        return Color.YELLOW;
    }

    @Override
    public String itemId() {
        return "S002";
    }
}
