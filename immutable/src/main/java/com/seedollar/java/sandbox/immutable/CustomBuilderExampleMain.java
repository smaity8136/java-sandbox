package com.seedollar.java.sandbox.immutable;

/**
 * Created by seedollar on 10/24/16.
 */
public class CustomBuilderExampleMain {

    public static void main(String[] args) {

        ImmutableScooter scooter = ImmutableScooter.builder().build();
        System.out.println("scooter = " + scooter.getClass().toString());

    }
}
