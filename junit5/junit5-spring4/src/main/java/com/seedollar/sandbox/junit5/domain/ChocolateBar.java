package com.seedollar.sandbox.junit5.domain;

public class ChocolateBar implements Snack {
    @Override
    public String tasteComment() {
        return "Coco, Sweet";
    }

    @Override
    public Color color() {
        return Color.BROWN;
    }

    @Override
    public String itemId() {
        return "S001";
    }
}
