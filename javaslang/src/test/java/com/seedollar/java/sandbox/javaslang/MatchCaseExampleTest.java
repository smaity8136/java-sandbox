package com.seedollar.java.sandbox.javaslang;


import javaslang.control.Option;
import javaslang.control.Try;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static javaslang.API.*;
import static javaslang.Patterns.Failure;
import static javaslang.Patterns.Some;
import static javaslang.Patterns.Success;
import static javaslang.Predicates.is;
import static javaslang.Predicates.isIn;

/**
 * Created by seedollar on 1/5/17.
 */
public class MatchCaseExampleTest {

    @Test
    @DisplayName("Show's how we can use a JavaSlang Match implementation using the EXACT atomic pattern")
    void testMatchExact() {
        final char salaryBand = 'C';

        // We use the "EXACT" atomic pattern $(value).
        String classification = javaslang.API.Match(salaryBand).of(
                Case($('A'), "Lanie"),
                Case($('B'), "Boss"),
                Case($('C'), "Subordinate"),
                Case($(), "Homeless?")
        );

        Assertions.assertEquals("Subordinate", classification);
    }

    @Test
    @DisplayName("Show's how we can use CONDITIONAL atomic pattern")
    void testMatchConditional() {
        final int priority = 2;

        String classification = Match(priority).of(
                Case($(is(5)), "Lanie"),
                Case($(is(3)), "Boss"),
                Case($(is(2)), "Pleb"),
                Case($(is(1)), "Bum")
        );

        Assertions.assertEquals("Pleb", classification);
    }

    @Test
    @DisplayName("Match Case implementation with Side Effects using the run() method")
    void testMatchCaseWithSideEffects() {
        final String selection = "4";

        Match(selection).of(
                Case(isIn("1", "2", "3"), o -> run(() -> {
                    System.out.println("Between 1 and 3: " + selection);
                    Assertions.fail("We should not get here");
                })),
                Case(isIn("4", "5", "6"), o -> run(() -> System.out.println("Between 4 and 6: " + selection))),
                Case($(), o -> run(() -> {
                    System.out.println("greater than 6: " + selection);
                    Assertions.fail("We should not get here");
                }))
        );
    }

    @Test
    @DisplayName("Match Case using a Try and Javaslang patterns Success and Failure")
    void testMatchCaseTry() {
        Try<String> divisibleTry = Try.of(() -> {
            int num = 1 / 0;
            return "Success";
        });

        Match(divisibleTry).of(
                Case(Success($()), o -> run(() -> {
                    System.out.println("Pass = " + o);
                    Assertions.assertEquals("Success", o);
                })),
                Case(Failure($()), o -> run(() -> {
                    System.out.println("Fail = " + o);
                    Assertions.assertTrue(o instanceof ArithmeticException);
                }))
        );
    }

    @Test
    @DisplayName("Match Case using Javaslang's Option")
    void testMatchCaseOption() {
        Option<Long> invalidRandomLong = Option.of(null);
        Option<Long> validRandomLong = Option.of(ThreadLocalRandom.current().nextLong());

        Long invalidResult = Match(invalidRandomLong).of(
                Case(None(), -1l)
        );
        Assertions.assertEquals(-1l, invalidResult.longValue());

        Option<Long> validResult = Match(validRandomLong).of(
                Case(Some($()), validRandomLong)
        );
        Assertions.assertTrue(validResult instanceof Option.Some);
    }
}
