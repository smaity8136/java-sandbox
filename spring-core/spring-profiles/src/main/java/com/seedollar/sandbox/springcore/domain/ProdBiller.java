package com.seedollar.sandbox.springcore.domain;

public class ProdBiller implements Biller {
    @Override
    public Double billableAmount() {
        return new Double("980.700");
    }
}
