package com.seedollar.sandbox.springcore.domain;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class AudiCar implements Car {

    @Override
    public void drive() {
        if (ThreadLocalRandom.current().nextInt() % 2 == 0) {
            throw new RuntimeException("Simulate car stalling");
        }
        System.out.println("DRIVE - Put into Drive mode");
        System.out.println("DRIVE - Press Accelerator Pedal");
    }

    @Override
    public void reverse() {
        System.out.println("REVERSE - Put into Reverse mode");
        System.out.println("REVERSE - Press Accelerator Pedal");
    }
}
