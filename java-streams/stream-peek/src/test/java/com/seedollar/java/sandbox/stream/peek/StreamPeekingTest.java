package com.seedollar.java.sandbox.stream.peek;

import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPeekingTest {

    @Test
    @DisplayName("Test to show how stream peeking works")
    public void testStreamPeeking() {
        ImmutableSet<Integer> integers = ImmutableSet.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> integerList = integers.stream().peek(n -> System.out.println("peek n = " + n))
                .filter(e -> e % 2 == 0)
                .peek(n -> System.out.println("filtered n = " + n))
                .map(e -> e * 10)
                .collect(Collectors.toList());

        System.out.println("integerList = " + integerList);
    }

    @Test
    @DisplayName("Test to show how stream peek can mutate elements")
    public void testStreamPeekingMutating() {
        List<MutableNumber> numberList = Stream.of(
                new MutableNumber(1),
                new MutableNumber(2),
                new MutableNumber(3),
                new MutableNumber(4),
                new MutableNumber(5),
                new MutableNumber(6),
                new MutableNumber(7),
                new MutableNumber(8),
                new MutableNumber(9),
                new MutableNumber(10))
                .peek(n -> System.out.println("peek = " + n))
                .peek(n -> n.setNumber(n.getNumber() * 10))
                .collect(Collectors.toList());
        System.out.println("numberList = " + numberList);
    }

    private class MutableNumber {
        private Integer number;

        public MutableNumber(Integer number) {
            this.number = number;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return number + "";
        }
    }
}
