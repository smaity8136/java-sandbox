package com.seedollar.java.sandbox.javaslang;

import javaslang.Predicates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;

/**
 * Created by seedollar on 1/5/17.
 */
public class MatchExampleTest {

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

//    @Test
//    @DisplayName("Show's how we can use CONDITIONAL atomic pattern")
//    void testMatchConditional() {
//        final double salary = 37000.00d;
//
//            Predicate<Double> isLanie = (sal) -> sal > 50000d;
//
//        String classification  = Match(salary).of(
//                Case($(isLanie)), "Lanie"),
//                Case($(Predicates.is(salary > 30000d), "Boss"),
//                Case($(Predicates.is(salary > 100000), "Lanie")
//        );
//    }


}
