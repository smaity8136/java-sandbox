package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class StringContainsDigitsOnlyTest {

    @Test
    @DisplayName("Illustrate how to determine if a string contains only digits using regular expression")
    public void testStringContainsOnlyDigits() {
        // Find any non-digit character, confirming it's not a number
        final Pattern anyNonDigitMatch1 = Pattern.compile(".*[\\D].*"); // Search for any non-digit character
        final Pattern anyNonDigitMatch2 = Pattern.compile(".*[^0-9].*");  // Search for any character not between 0 and 9.
        final String numberString = "563423";
        Assertions.assertFalse(anyNonDigitMatch1.matcher(numberString).matches());
        Assertions.assertFalse(anyNonDigitMatch2.matcher(numberString).matches());
        final String wordString = "number";
        Assertions.assertTrue(anyNonDigitMatch1.matcher(wordString).matches());
        Assertions.assertTrue(anyNonDigitMatch2.matcher(wordString).matches());

    }
}
