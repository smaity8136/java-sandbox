package com.seedollar.java.sandbox.fibonacci;

import java.util.Arrays;

public class FibonacciSeqIterativeApplication {

    public static void main(String[] args) {
        int[] fibonacci = new int[20];

        fibonacci[0] = 0;

        int prevPrev = 0;
        int prev = 1;
        for (int x = 1; x < 20; x++) {
            fibonacci[x] = prevPrev + prev;
            prev = prevPrev;
            prevPrev = fibonacci[x];
            System.out.println("x = " + x);
            System.out.println("prev = " + prev);
            System.out.println("prevPrev = " + prevPrev);
        }

        System.out.println("fibonacci = " + Arrays.toString(fibonacci));
    }
}
