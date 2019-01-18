package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class CountVowelsInStringTest {

    @Test
    @DisplayName("Illustrate how we can count the number of vowels and consonants in a string")
    public void testCountVowelsInString() {
        String word = "The regular mash attack";
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int vowelCount = 0;
        int consonantCount = 0;
        for (Character c : word.toCharArray()) {
            if (vowels.contains(c)) {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }
        System.out.println("vowelCount = " + vowelCount);
        System.out.println("consonantCount = " + consonantCount);
        System.out.println("totalCharCount = " + word.length());
    }
}