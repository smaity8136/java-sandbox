package com.seedollar.java.sandbox.javaslang.unapply;

import com.seedollar.java.sandbox.javaslang.unapply.model.Dog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static javaslang.API.*;
import static javaslang.API.Match;
import static com.seedollar.java.sandbox.javaslang.unapply.CustomPatterns.*;

/**
 * Created by seedollar on 1/18/17.
 *
 * This test class will illustrate how we can create custom patterns in order to implement unapply functions for matching purposes.
 *
 * It requires the io.javaslang:javaslang-match dependency.
 *
 * I compiled the Custom.java class which generates the CustomPatterns.java class automatically.
 *
 */
public class ObjectDecompositionTest {

    @Test
    @DisplayName("This test will illustrate how we can create a custom unapply pattern which allows us to decompose an object for matching")
    public void objectDecomposition() {
        Dog testDog = new Dog("Snoopy", "Beagle");

        String breedResult = Match(testDog).of(
                Case(Dog($("Snoopy"), $()), (name, breed) -> "The breed should be " + breed),
                Case(Dog($(), $()), "The dog cannot be a Beagle")
        );
        Assertions.assertEquals("The breed should be Beagle", breedResult);
    }

}
