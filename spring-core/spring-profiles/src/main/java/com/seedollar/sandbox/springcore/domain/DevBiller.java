package com.seedollar.sandbox.springcore.domain;

public class DevBiller implements Biller {
    @Override
    public Double billableAmount() {
        return new Double("124.00");
    }
}
