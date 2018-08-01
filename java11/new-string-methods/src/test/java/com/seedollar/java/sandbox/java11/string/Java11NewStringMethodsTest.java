package com.seedollar.java.sandbox.java11.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Java11NewStringMethodsTest {

    @Test
    @DisplayName("Test to show how to use the String.repeat() method")
    public void testStringRepeat() {
        Map<String, String> headingMap = Arrays.asList("NAME", "SURNAME", "DESCRIPTION", "MOBILE").stream().collect(Collectors.toMap(heading -> heading, this::buildHeadingUnderscore));
        System.out.println("headingMap = " + headingMap);
    }

    @Test
    public void testLines() {
        final String ezekiel2517 = "The path of the righteous man is beset on all sides by the inequities of the selfish and the tyranny of evil men. \n" +
                "Blessed is he who, in the name of charity and good will, shepherds the weak through the valley of darkness, for he is truly his brother's keeper and the finder of lost children. \n" +
                "And I will strike down upon thee with great vengeance and furious anger those who attempt to poison and destroy my brothers. \n" +
                "And you will know my name is the Lord when I lay my vengeance upon thee";
        ezekiel2517.lines().forEach(System.out::println);
    }

    @Test
    public void testStrip() {
        String sentence = "    This is a sentence prefixed and postfixed with spaces.  ";
        System.out.println("[START]" + sentence.strip() + "[DONE]");
        System.out.println("[START]" + sentence.stripTrailing() + "[DONE]");
        System.out.println("[START]" + sentence.stripLeading() + "[DONE]");
    }

    @Test
    public void testIsBlank() {
        final String blankString1 = "";
        final String blankString2 = "  ";
        final String blankString3 = "no";
        System.out.println("Is blankString1 blank = " + blankString1.isBlank());
        System.out.println("Is blankString2 blank = " + blankString2.isBlank());
        System.out.println("Is blankString3 blank = " + blankString3.isBlank());
    }

    @Test
    public void testToStringCharacter() {
        Assertions.assertEquals("A", Character.toString(65));
    }

    private String buildHeadingUnderscore(String heading) {
        return "_".repeat(heading.length()); // This will simply repeat the underscore character of x length
    }
}
