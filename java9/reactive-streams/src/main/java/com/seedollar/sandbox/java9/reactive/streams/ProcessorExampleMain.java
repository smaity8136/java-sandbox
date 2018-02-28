package com.seedollar.sandbox.java9.reactive.streams;

import com.seedollar.sandbox.java9.reactive.streams.processor.AddTenProcessor;
import com.seedollar.sandbox.java9.reactive.streams.subscriber.PrintSubscriber;

import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

public class ProcessorExampleMain {

    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        AddTenProcessor processor = new AddTenProcessor();
        PrintSubscriber printSubscriber = new PrintSubscriber();
        publisher.subscribe(processor);
        processor.subscribe(printSubscriber);

        System.out.println("Publishing numbers...");

        IntStream.range(1, 10).forEach(publisher::submit);

        Thread.sleep(2000);
        publisher.close();
    }
}
