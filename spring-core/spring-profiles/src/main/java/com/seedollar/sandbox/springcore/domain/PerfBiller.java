package com.seedollar.sandbox.springcore.domain;

public class PerfBiller implements Biller {
    @Override
    public Double billableAmount() {
        return new Double("392.00");
    }
}
