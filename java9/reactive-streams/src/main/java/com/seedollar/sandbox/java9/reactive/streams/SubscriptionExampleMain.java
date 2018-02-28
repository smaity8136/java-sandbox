package com.seedollar.sandbox.java9.reactive.streams;

import com.seedollar.sandbox.java9.reactive.streams.subscriber.PrintSubscriber;

import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

public class SubscriptionExampleMain {

    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        publisher.subscribe(new PrintSubscriber());

        System.out.println("Publishing the numbers...");

        IntStream.range(1, 10).forEach(publisher::submit);

        Thread.sleep(1000);
        publisher.close();
    }
}
