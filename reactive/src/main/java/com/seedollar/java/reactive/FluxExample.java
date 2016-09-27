package com.seedollar.java.reactive;

import reactor.core.publisher.Flux;

/**
 * Created by seedollar on 9/26/16.
 *
 * This flux example is all declarative (no data is processed) until the subscribe() method is invoked.
 */
public class FluxExample {

    public static void main(String[] args) {

        Flux<String> flux = Flux.just("red", "white", "blue");

        flux
                .log()
                .map(String::toUpperCase)
                .doOnNext(str -> {
                    if (str.equalsIgnoreCase("red")) {
                        System.out.println("RED processed");
                    }
                })
                .subscribe(System.out::println);
    }


}
