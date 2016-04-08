package com.seedollar.java.memcached.configuration;

import net.spy.memcached.MemcachedClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by seedollar on 4/8/16.
 */
@Configuration
public class MemcachedConfiguration {

    @Bean
    public MemcachedClient memcachedClient() {
        try {
            MemcachedClient memcachedClient = new MemcachedClient(memcachedSocketAddress());
            return memcachedClient;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot create memcached client instance");
        }
    }

    @Bean
    public InetSocketAddress memcachedSocketAddress() {
        return new InetSocketAddress("localhost", 11211);
    }
}
