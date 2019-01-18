package com.seedollar.java.sandbox.fibonacci;

/**
 * Created by seedollar on 11/16/17.
 */
public class FibonacciSeqRecursiveApplication {

    public static void main(String[] args) {

        for (int x=1; x< 10; x++)
            System.out.println(generateFibonacci(x));

    }

    private static int generateFibonacci(int number) {
        if (number == 1) {
            return 1;
        }
        if (number == 2) {
            return 1;
        }
        System.out.println("number = " + number);
        return generateFibonacci(number-2) + generateFibonacci(number-1);
    }
}
