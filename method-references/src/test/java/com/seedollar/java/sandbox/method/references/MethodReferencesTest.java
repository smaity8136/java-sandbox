package com.seedollar.java.sandbox.method.references;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * There are 4 types of method references:
 *
 * 1) Static methods
 * 2) Instance object methods
 * 3) Class object methods
 * 4) No Argument Constructors
 */
public class MethodReferencesTest {

    @Test
    @DisplayName("Illustrate static method reference")
    public void testStaticMethodReference() {

        final String string1 = "Method";
        final String string2 = "References";

        List<String> stringList = Stream.of(string1, string2).map(MethodReferencesTest::upperCase).collect(Collectors.toList());
        Assertions.assertTrue(stringList.contains("METHOD"));
        Assertions.assertTrue(stringList.contains("REFERENCES"));
    }

    @Test
    @DisplayName("Illustrate instance method reference")
    public void testInstanceMethodReference() {
        final String string1 = "one";
        final String string2 = "two";
        final String string3 = "three";
        final String string4 = "four";
        StringComparator myStringComparator = new StringComparator();

        List<String> strings = Arrays.asList(string1, string2, string3, string4);
        Collections.sort(strings, myStringComparator::compare);
        Assertions.assertEquals(string3, strings.get(0));
    }

    @Test
    @DisplayName("Illustrate a Class method reference")
    public void testClassMethodReference() {
        final String string1 = "ONE";
        final String string2 = "TWO";
        final String string3 = "THREE";
        final String string4 = "FOUR";
        List<String> stringList = Arrays.asList(string1, string2, string3, string4).stream().map(String::toLowerCase).collect(Collectors.toList());
        Assertions.assertTrue(stringList.stream().allMatch(s -> s.chars().allMatch(Character::isLowerCase)));
    }

    @Test
    @DisplayName("Illustrate a no arg constructor as a method reference")
    public void testConstructorMethodReference() {
        Function<String, GreetingDecorator> instance = GreetingDecorator::new;
        Assertions.assertEquals("Ola, Method reference", instance.apply("Method reference").decorate());
    }


    public static final String upperCase(String value) {
        return value.toUpperCase();
    }

    class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() == o2.length()) {
                return 0;
            } else if (o1.length() < o2.length()) {
                return 1;
            }
            return -1;
        }
    }

    class GreetingDecorator {

        private String value;

        public GreetingDecorator(String value) {
            this.value = value;
        }

        public String decorate() {
            return "Ola, " + value;
        }

    }
}
