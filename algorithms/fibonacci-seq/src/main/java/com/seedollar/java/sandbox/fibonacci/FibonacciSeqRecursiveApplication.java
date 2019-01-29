package com.seedollar.java.sandbox.fibonacci;

import java.util.Arrays;

/**
 * Created by seedollar on 11/16/17.
 */
public class FibonacciSeqRecursiveApplication {

    public static void main(String[] args) {
        int[] fibonacci = new int[20];
        fibonacci[0] = 0;

        for (int x=1; x< fibonacci.length; x++) {
            fibonacci[x] = generateFibonacci(x);
        }
        System.out.println(Arrays.toString(fibonacci));

    }

    private static int generateFibonacci(int number) {
        if (number == 1) {
            return 1;
        }
        if (number == 2) {
            return 1;
        }
        return generateFibonacci(number-2) + generateFibonacci(number-1);
    }
}
