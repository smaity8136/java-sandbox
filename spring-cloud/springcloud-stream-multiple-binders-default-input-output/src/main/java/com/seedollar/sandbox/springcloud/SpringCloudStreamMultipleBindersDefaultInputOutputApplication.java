package com.seedollar.sandbox.springcloud;

import com.google.common.collect.Lists;
import com.seedollar.sandbox.springcloud.sink.VegetableSink;
import com.seedollar.sandbox.springcloud.source.FruitSource;
import io.vavr.Function0;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;

import java.util.List;
import java.util.Random;

/**
 * Created by seedollar on 7/25/17.
 */
@SpringBootApplication
@EnableBinding(value = {FruitSource.class, VegetableSink.class})
public class SpringCloudStreamMultipleBindersDefaultInputOutputApplication {

    final static List<String> FRUITS = Lists.newArrayList("APPLE", "ORANGE", "PEACH", "BANANA", "GRAPES");
    final static List<String> VEGETABLES = Lists.newArrayList("CABBAGE", "BROCCOLI", "SPINACH", "BABY MARROW", "CARROTS");

    Function0<String> generateRandomFruitFunction = () -> FRUITS.get(new Random().nextInt(FRUITS.size()-1));
    Function0<String> generateRandomVegetableFunction = () -> VEGETABLES.get(new Random().nextInt(VEGETABLES.size()-1));

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamMultipleBindersDefaultInputOutputApplication.class, args);
    }

    @InboundChannelAdapter(channel = )
}
