package com.seedollar.sandbox.junit5.domain;

/**
 * Created by seedollar on 7/11/17.
 */
public class Q5 implements Audi, Car {
    @Override
    public String getModel() {
        return "Q5";
    }

    @Override
    public boolean isQuattro() {
        return true;
    }
}
