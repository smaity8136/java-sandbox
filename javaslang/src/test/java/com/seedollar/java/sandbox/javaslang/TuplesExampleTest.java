package com.seedollar.java.sandbox.javaslang;

import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.Tuple3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by seedollar on 1/3/17.
 */
public class TuplesExampleTest {

    @Test
    @DisplayName("Illustrate how to create JavaSlang Tuple")
    void testCreateTuple() {
        Tuple3<String, Integer, Long> tuple3 = Tuple.of("StringValue", ThreadLocalRandom.current().nextInt(), ThreadLocalRandom.current().nextLong());
        Assertions.assertTrue(tuple3._1() instanceof String);
        Assertions.assertTrue(tuple3._2() instanceof Integer);
        Assertions.assertTrue(tuple3._3() instanceof Long);
    }

    @Test
    @DisplayName("Create a Tuple map")
    void testCreateTupleMap() {
        Tuple2<String, Double> originalTuple = Tuple.of("Blueray Movie", 200.99d);

        // We can convert this to a map, using lambdas to map the key and value to a new tuple of type 2 capacity.
        Tuple2<String, Double> tupleMap = originalTuple.map(
                product -> product + " -Sold",
                price -> price * .05);

        Assertions.assertEquals("Blueray Movie -Sold", tupleMap._1());
        Assertions.assertEquals(10.049500274658204d, tupleMap._2(), .001);

        // We can also transform "apply()" the original tuple to some other datatype
        Double summedValue = originalTuple.apply((i, p) -> i.length() + p);
        Assertions.assertEquals(213, summedValue, 1);


    }
}
