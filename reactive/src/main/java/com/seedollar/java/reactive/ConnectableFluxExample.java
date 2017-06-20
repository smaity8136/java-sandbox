package com.seedollar.java.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * Created by seedollar on 5/22/17.
 */
public class ConnectableFluxExample {

    public static void main(String[] args) throws InterruptedException {
        ConnectableFlux<Object> hotStream = Flux.create(sink -> {
            while (true) {
                sink.next(System.currentTimeMillis());
            }
        })
                .sample(Duration.ofSeconds(2)) // THROTTLING of 2 seconds per element
                .publish();

        hotStream.log().subscribe(new Subscriber<Object>() {

            CountDownLatch latch = new CountDownLatch(100);

            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(1000);
            }

            @Override
            public void onNext(Object o) {
                latch.countDown();
                System.out.println("latch = " + latch.getCount());
                if (latch.getCount() == 10) {
                    throw new RuntimeException("Latch completed countdown");
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("throwable = " + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        });

        hotStream.connect();

    }
}
