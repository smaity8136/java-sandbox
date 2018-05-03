package com.seedollar.java9;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class ObjectsTest {

    @Test
    public void testRequireNonNull() {
        final String nullString = null;
        Assertions.assertThrows(NullPointerException.class, () -> Objects.requireNonNull(nullString, "This variable cannot be null"));
    }

    @Test
    public void testRequireNonNullOrElse() {
        final String nullString = null;
        Assertions.assertEquals("elseString", Objects.requireNonNullElse(nullString, "elseString"));
    }

    @Test
    public void testRequireNonNullElseGet() {
        final String nullString = null;
        Assertions.assertNotNull(Objects.requireNonNullElseGet(nullString, () -> {
            int i = ThreadLocalRandom.current().nextInt();
            return "deferredString" + i;
        }));

    }
}