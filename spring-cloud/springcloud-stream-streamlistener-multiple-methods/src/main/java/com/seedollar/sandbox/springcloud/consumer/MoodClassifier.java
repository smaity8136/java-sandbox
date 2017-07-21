package com.seedollar.sandbox.springcloud.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import com.seedollar.sandbox.springcloud.sink.ColorSink;

/**
 * Created by seedollar on 7/18/17.
 */
@EnableBinding(ColorSink.class)
public class MoodClassifier {

    @StreamListener(ColorSink.INPUT)
    public void audit(Message<String> message) {
        System.out.println(String.format("Mood received: %s", message.getPayload()));
        System.out.println(String.format("Message headers: %s", message.getHeaders()));
    }

    @StreamListener(target = ColorSink.INPUT, condition = "headers['mood']=='RED'")
    public void angry() {
        System.out.println("ANGRY!!!!!");
    }

    @StreamListener(target = ColorSink.INPUT, condition = "headers['mood']=='BLUE'")
    public void sad() {
        System.out.println("SAD.......");
    }

    @StreamListener(target = ColorSink.INPUT, condition = "headers['mood']=='GREEN'")
    public void sick() {
        System.out.println("SICK .. ..");
    }

    @StreamListener(target = ColorSink.INPUT, condition = "headers['mood']=='ORANGE'")
    public void warm() {
        System.out.println("WARM######");
    }

    @StreamListener(target = ColorSink.INPUT, condition = "headers['mood']=='YELLOW'")
    public void enlightened() {
        System.out.println("ENLIGHTENED ^!");
    }
}
