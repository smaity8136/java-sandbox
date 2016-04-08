package com.seedollar.java.memcached.client;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by seedollar on 4/8/16.
 */
@Configuration
public class SpyMemcachedClient {

    @Autowired
    private MemcachedClient memcachedClient;

    public void setupCache() {
        // Cache numbers for 3600 seconds into seperate key caches
        List<Integer> collect = IntStream.rangeClosed(1, 50).mapToObj(integer -> new Integer(integer)).collect(Collectors.toList());
        collect.stream().forEach(i -> memcachedClient.set("foo"+i, 3600, String.valueOf(i*100)));
    }

    public void findCache(String key) {
        List<Integer> collect = IntStream.rangeClosed(1, 50).mapToObj(integer -> new Integer(integer)).collect(Collectors.toList());
        List<String> cacheContents = collect.stream().map(i -> (String) memcachedClient.get("foo" + i)).collect(Collectors.toList());
        System.out.println("cacheResults = " + cacheContents);
    }


}
