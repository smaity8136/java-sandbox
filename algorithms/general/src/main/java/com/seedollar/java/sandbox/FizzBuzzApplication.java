package com.seedollar.java.sandbox;

/**
 * Created by seedollar on 11/17/17.
 */
public class FizzBuzzApplication {

    public static void main(String[] args) {

        final int count = 20;

        for (int x = 1; x <= count; x++) {
            String temp = x + "";
            if (x % 3 == 0)
                temp = "Fizz";

            if (x % 5 == 0)
                temp += "Buzz";

            System.out.println(temp);
        }
    }
}
