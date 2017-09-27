package com.seedollar.sandbox.springcore.domain;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class AudiCar implements Car {

    private int currentGear;

    @Override
    public void drive() {
        if (ThreadLocalRandom.current().nextInt() % 2 == 0) {
            throw new RuntimeException("Simulate car stalling during DRIVING");
        }
        System.out.println("DRIVE - Put into Drive mode");
        System.out.println("DRIVE - Press Accelerator Pedal");
    }

    @Override
    public void reverse() {
        if (ThreadLocalRandom.current().nextInt() % 2 == 0) {
            throw new RuntimeException("Simulate car stalling during REVERSING");
        }
        System.out.println("REVERSE - Put into Reverse mode");
        System.out.println("REVERSE - Press Accelerator Pedal");
    }

    @Override
    public void changeGear(int gearNumber) {
        System.out.println("Gear changed to number: " + gearNumber);
        this.currentGear = gearNumber;
    }
}
