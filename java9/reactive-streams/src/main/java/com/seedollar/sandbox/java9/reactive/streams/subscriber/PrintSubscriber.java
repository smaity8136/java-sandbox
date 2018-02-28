package com.seedollar.sandbox.java9.reactive.streams.subscriber;

import java.util.concurrent.Flow;

public class PrintSubscriber implements Flow.Subscriber<Integer> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println("Received number: " + item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error occurred: " + throwable.getCause());
    }

    @Override
    public void onComplete() {
        System.out.println("PrintSubscriber is complete.");
    }
}
