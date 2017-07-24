package com.seedollar.sandbox.junit5.domain;

/**
 * Created by seedollar on 7/11/17.
 */
public class M1 implements BMW, Car {
    @Override
    public String getModel() {
        return "M1";
    }

    @Override
    public boolean isRunOnFlats() {
        return true;
    }
}
