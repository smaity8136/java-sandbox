package com.seedollar.java.sandbox.javaslang;

import javaslang.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 1/3/17.
 */
public class OptionExampleTest {

    @Test
    @DisplayName("Show how to use a Javaslang Option")
    public void testOption1() {
        Option<String> cannotBeNull = Option.of(null);
        Assertions.assertNotNull(cannotBeNull.orElse(Option.none()));
    }
}
