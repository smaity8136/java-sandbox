package com.seedollar.sandbox.junit5.domain;

public interface Snack {

    enum Color {RED, YELLOW, GREEN, ORANGE, BROWN}

    String tasteComment();

    Color color();

    String itemId();
}
