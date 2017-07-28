package com.seedollar.sandbox.springcloud;

import com.seedollar.sandbox.springcloud.source.RandomNumberSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;

import java.util.Random;

/**
 * Created by seedollar on 7/25/17.
 */
@SpringBootApplication
@EnableBinding(RandomNumberSource.class)
public class SpringCloudStreamDefaultBinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamDefaultBinderApplication.class, args);
    }

    @InboundChannelAdapter(channel = RandomNumberSource.OUTPUT)
    public Integer generateRandomNumber() {
        return new Random().nextInt();
    }
}
