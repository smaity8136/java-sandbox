package com.seedollar.java.sandbox.java.bin.utils;

public class JMapExample {
    private static final int NUM_NUMS = 10 * 1000 * 1000;
    private static Long[] nums = new Long[NUM_NUMS];
    public static void main(String args[]) throws InterruptedException {
        for (int i = 0; i < NUM_NUMS; i++) {
            nums[i] = (long) i;
        }
        System.out.println("Initialized array; going to sleep...");
        Thread.sleep(1000000000);
    }
}
