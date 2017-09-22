package com.seedollar.sandbox.springcore.domain;

public class SupremeCourtJudge implements Judge {

    private String identifier;

    public SupremeCourtJudge(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }
}
