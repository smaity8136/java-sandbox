package com.seedollar.java.sandbox.javaslang;

import javaslang.CheckedFunction2;
import javaslang.Function4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 1/3/17.
 */
public class FunctionsExampleTest {

    @Test
    @DisplayName("Show how to create JavaSlang Function implementation")
    void testCreateFunction() {
        // We create a Function which will accept 4 parameters of different datatypes and returns a Long value as a result.
        Function4<String, Boolean, Integer, String, Integer> computation = (name, flag, age, designation) -> (name.length() + flag.toString().length() + age + designation.length());
        Integer result = computation.apply("Jimmy", Boolean.FALSE, 35, "Manager");
        Assertions.assertEquals(52, result.longValue(), "The aggregation of the parameters should be 52");
    }

    @Test
    @DisplayName("Show how to create a JavaSlang CheckedFunction implementation")
    void testCheckedFunction() {
        CheckedFunction2<String, String, Integer> checkedFunction2 = (firstName, lastName) -> {
            if (firstName.length() < 5) {
                throw new Exception("FirstName must be greater that 5 characters in length");
            }
            return firstName.length() + lastName.length();
        };

        try {
            checkedFunction2.apply("John6", "Carter");
        } catch (Throwable throwable) {
            Assertions.assertTrue(throwable instanceof Exception);
            Assertions.assertTrue(throwable.getMessage().contains("FirstName must be greater that 5 characters in length"));
            throwable.printStackTrace();
        }
    }


}
