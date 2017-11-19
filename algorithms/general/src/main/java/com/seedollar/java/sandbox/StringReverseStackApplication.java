package com.seedollar.java.sandbox;

import java.util.Stack;

/**
 * Created by seedollar on 11/18/17.
 */
public class StringReverseStackApplication {

    public static void main(String[] args) {
        final String reverseString = "Reverse this string";
        Stack<Character> stringReverseStack = new Stack<>();

        for (char ch : reverseString.toCharArray()) {
            stringReverseStack.push(ch);
        }

        StringBuilder reversedStringBuilder = new StringBuilder();

        while(!stringReverseStack.isEmpty()) {
            reversedStringBuilder.append(stringReverseStack.pop());
        }
        System.out.println(reversedStringBuilder.toString());
    }
}
