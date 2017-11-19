package com.seedollar.java.sandbox;

/**
 * Created by seedollar on 11/17/17.
 */
public class StringReverseRecursivelyApplication {

    public static void main(String[] args) {
        final String word = "I am screwed";

        System.out.println("word = " + reverse(word));
    }

    private static String reverse(String str) {
        if (str.isEmpty()) {
            return str;
        }

        return reverse(str.substring(1)) + str.charAt(0);
    }
}
