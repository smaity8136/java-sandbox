package com.seedollar.java.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * Created by seedollar on 9/27/16.
 *
 * This example shows how you can make the request "bounded" (limited) by overriding the Subscriber.
 */
public class FluxSubscribeExample {

    public static void main(String[] args) {
        Flux<String> flux = Flux.just("BMW", "Audi", "Toyota", "Volkswagen");

        flux.log().subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(2); // Make the request "bounded" to 2 elements
            }

            @Override
            public void onNext(String element) {
                System.out.println("element = " + element);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
