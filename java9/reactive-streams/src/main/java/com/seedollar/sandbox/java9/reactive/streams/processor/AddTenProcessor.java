package com.seedollar.sandbox.java9.reactive.streams.processor;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class AddTenProcessor extends SubmissionPublisher<Integer> implements Flow.Subscriber<Integer> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        submit(item + 10);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        closeExceptionally(throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("AddTenProcessor completed");
        close();
    }
}
