package com.seedollar.java.sandbox.optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void testOptionalEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        Assertions.assertFalse(emptyOptional.isPresent());
    }

    @Test
    public void testOptionalOf() {
        String test = null;
        try {
            Optional.of(test);
            Assertions.fail("We should have failed");
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testOptionalOfNullable() {
        String test = null;
        try {
            Optional<String> testOptional = Optional.ofNullable(test);
            Assertions.assertFalse(testOptional.isPresent());
        } catch (Exception e) {
            Assertions.fail("We should not have failed");
        }
    }

    @Test
    public void testOptionalMap() {
        Optional testOptional = Optional.of("test");
        Assertions.assertEquals("testmapped", testOptional.map((s) -> s + "mapped").get());
    }

    @Test
    public void testOptionalOrElse() {
        Optional testOptional = Optional.empty();
        // orElse will be invoked if the Optional value is null
        Assertions.assertEquals("orElse", testOptional.map(s -> s + "mapped").orElse("orElse"));
    }

    @Test
    public void testOptionalOrElseGet() {
        Optional testOptional = Optional.empty();
        Assertions.assertEquals("orElseGet", testOptional.map(s -> s + "mapped").orElseGet(() -> "orElseGet"));
    }
}
