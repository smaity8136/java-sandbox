package com.seedollar.java.reactive;

import reactor.core.publisher.Flux;

/**
 * Created by seedollar on 5/22/17.
 */
public class FluxCombineStreamsExample {

    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.just(1,2,3,4);

        numbers
                .log()
                .map(n -> n *2)
                .zipWith(Flux.range(0, 10), (two, one) -> String.format("Num1: %d, Num2: %s", one, two))
                .subscribe(System.out::println);
    }
}
