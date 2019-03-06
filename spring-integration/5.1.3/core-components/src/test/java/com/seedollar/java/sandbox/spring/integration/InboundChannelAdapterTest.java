package com.seedollar.java.sandbox.spring.integration;

import com.seedollar.java.sandbox.spring.intergration.configuration.InboundChannelAdapterConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest(classes = InboundChannelAdapterConfiguration.class)
public class InboundChannelAdapterTest {


    @Autowired
    private InboundChannelAdapterConfiguration inboundChannelAdapterConfiguration;

    @Test
    @DisplayName("Illustration of an inbound-channel-adapter")
    public void testInboundChannelAdapter() throws InterruptedException {

        IntStream.range(0, 10).forEach(num -> inboundChannelAdapterConfiguration.handleMessage());

        Thread.sleep(2000);
    }
}
