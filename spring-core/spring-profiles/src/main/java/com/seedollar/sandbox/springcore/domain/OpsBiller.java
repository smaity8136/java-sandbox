package com.seedollar.sandbox.springcore.domain;

public class OpsBiller implements Biller {

    private String deploymentMethod;

    public OpsBiller(String deploymentMethod) {
        this.deploymentMethod = deploymentMethod;
    }

    @Override
    public Double billableAmount() {
        return new Double("390.85");
    }

    public String getDeploymentMethod() {
        return deploymentMethod;
    }
}
