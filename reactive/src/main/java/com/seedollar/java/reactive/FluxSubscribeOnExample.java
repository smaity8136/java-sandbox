package com.seedollar.java.reactive;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Created by seedollar on 9/27/16.
 */
public class FluxSubscribeOnExample {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6, 7);

        flux
                .log()
                .filter(num -> num % 2 == 0)
                .subscribeOn(Schedulers.parallel()) // Run the processing on backgound thread.
                .subscribe(System.out::println, 2);
    }
}
