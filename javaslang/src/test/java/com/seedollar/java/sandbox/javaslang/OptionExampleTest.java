package com.seedollar.java.sandbox.javaslang;

import javaslang.control.Option;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 1/3/17.
 */
public class OptionExampleTest {

    @Test
    @DisplayName("Shows how a JavaSlang Option either results in a None or Some instance, never NULL")
    void testOption1() {
        Option<String> testNoneOption = Option.of(null);
        Option<String> testSomeOption = Option.of("test");

        Assertions.assertTrue(testNoneOption instanceof Option.None);
        Assertions.assertTrue(testSomeOption instanceof Option.Some);
    }

    @Test
    @DisplayName("Show how to use a Javaslang Option")
    public void testOption2() {
        Option<String> cannotBeNull = Option.of(null);
        Assertions.assertNotNull(cannotBeNull.orElse(Option.none()));
    }

    @Test
    @DisplayName("A null initialization of Option")
    void testNullInitializationOption() {
        final String testValue = null;
        Option<String> nullInitializationOption = Option.of(testValue);
        Assert.assertEquals("valueWasNull", nullInitializationOption.getOrElse("valueWasNull"));
    }
}
