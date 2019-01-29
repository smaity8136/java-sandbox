package com.seedollar.java.sandbox;

/**
 * Created by seedollar on 11/17/17.
 */
public class StringReverseApplication {

    public static void main(String[] args) {

        final String value = "I am screwed";

        StringBuilder stringBuilder = new StringBuilder();
        for (int x =value.length()-1; x>=0; x--) {
            stringBuilder.append(value.toCharArray()[x]);
        }

        System.out.println("stringBuilder = " + stringBuilder.toString());

    }
}
