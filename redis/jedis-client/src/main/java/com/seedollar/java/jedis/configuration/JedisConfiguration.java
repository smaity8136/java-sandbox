package com.seedollar.java.jedis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by seedollar on 4/11/16.
 */
@Configuration
public class JedisConfiguration {

    @Bean
    public JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool("localhost", 6379);
        return jedisPool;
    }

    @Bean
    public Jedis jedis() {
        return jedisPool().getResource();
    }
}
