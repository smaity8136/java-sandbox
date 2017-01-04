package com.seedollar.java.sandbox.javaslang.validation;

import javaslang.collection.List;
import javaslang.control.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Created by seedollar on 1/4/17.
 */
public class ValidationExampleTest {

    @Test
    @DisplayName("Shows how to use the Validation Javaslang implementation, which a a 'applicative functor'")
    void testValidation() {
        final CatValidator catValidator = new CatValidator();

        // Validation that passes
        Validation<List<String>, Cat> successfulValidation = catValidator.validateCat("tom", "ginger");
        Assertions.assertEquals("ginger", successfulValidation.get().getBreed());

        // Validation that fails, should append 2 errors
        Validation<List<String>, Cat> failedValidation = catValidator.validateCat("", "brown");
        Assertions.assertTrue(failedValidation.getError().length() == 2);
    }


}
