package com.seedollar.sandbox.springcore.domain;

public class UatBiller implements Biller {
    @Override
    public Double billableAmount() {
        return new Double("680.90");
    }
}
