package com.seedollar.junit5;

import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Junit5ParameterizedTest {

    public enum Status {
        OPEN, PROCESSING, CLOSED;
    }

    @ParameterizedTest
    @ValueSource(ints = {2,4,6,8})
    @DisplayName("Parameterized test to test an array of integers, verifying that they are even numbers, using @ValueSource")
    public void testEvenNumbers(int num) {
        Assertions.assertEquals(0, num % 2);
    }

    @ParameterizedTest
    @ValueSource(chars = {'t', 'o', 'd', 'b', 'f'})
    @DisplayName("Parameterized test that tests an array of chars[]")
    public void testIsAlphabeticalCharacter(char ch) {
        Assertions.assertTrue(Character.isAlphabetic(ch));
    }

    @ParameterizedTest
    @EnumSource(value = Status.class, names = {"OPEN", "CLOSED"})
    @DisplayName("Parameterized test that uses @EnumSource")
    public void testEnumTerminatorModes(Status mode) {
        final Set<Status> terminatorModes = Set.of(Status.OPEN, Status.CLOSED);
        terminatorModes.contains(mode);
    }

    @ParameterizedTest
    @MethodSource("applicableDiscounts")
    @DisplayName("Parameterized test to reference a static method and assert it's value")
    public void testMethodSource(Double discount) {
        Assert.assertTrue(discount < 10);
    }

    @ParameterizedTest
    @CsvSource(value = {"Jim Batton, 26", "Carl Sanders, 62", "Eric Stoltz, 35"}, delimiter = '|')
    @DisplayName("Parameterized test to illustate testing CSV entries")
    public void testCsvSource(String csvLine) {
        int age = Integer.parseInt(csvLine.split(",")[1].trim());
        Assertions.assertTrue(age < 50);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/groups.csv", delimiter = '\n', numLinesToSkip = 1)
    @DisplayName("Parameterized test to illustrate the processing of a CSV file")
    public void testCsvFileSource(String csvLine) {
        Assertions.assertFalse(csvLine.contains("Coach"));
    }

    @ParameterizedTest(name = "{index} => price={0}, discountPercentage={1}, discountAmount={2}")
    @ArgumentsSource(DiscountCalculationArguments.class)
    @DisplayName("Parameterized test to illustate how to use the @ArgumentsSource to generate a stream of arguments to be asserted")
    public void testArgumentsSource(Double price, Double discountPercentage, Double discountAmount) {
        Assertions.assertEquals(discountAmount, price * (discountPercentage/100), 0.001);
    }

    static DoubleStream applicableDiscounts() {
       return DoubleStream.of(5.6d, 7.3d, 9.8d);
    }

    static class DiscountCalculationArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(50d, 3d, 1.5d),
                    Arguments.of(50d, 6d, 3d),
                    Arguments.of(50d, 7.5d, 3.75d),
                    Arguments.of(50d, 10d, 5d)
            );
        }
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @ArgumentsSource(CustomArgumentProvider.class)
    void sum(int a, int b, int sum) {
        Assertions.assertEquals(sum, a + b);
    }

    static class CustomArgumentProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(1, 1, 2),
                    Arguments.of(2, 3, 5)
            );
        }
    }
}
