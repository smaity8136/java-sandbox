package com.seedollar.sandbox.springcore.domain;

public class ParallelParking implements Parking {
    @Override
    public void park() {
        System.out.println("Park Parallel to the curb");
    }
}
