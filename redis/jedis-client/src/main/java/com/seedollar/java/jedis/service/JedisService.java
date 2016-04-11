package com.seedollar.java.jedis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by seedollar on 4/11/16.
 */
@Service
public class JedisService {

    @Autowired
    private Jedis jedis;

    public void insertKeyValue(String key, String value) {
        jedis.set(key, value);
    }

    public String getValueForKey(String key) {
        return jedis.get(key);
    }

    public void addToListAtStart(String key, String value) {
        jedis.lpush(key, value);
    }

    public void addToListAtEnd(String key, String value) {
        jedis.rpush(key, value);
    }

    public List getFullListForKey(String key) {
        return jedis.lrange(key, 0, -1);
    }

    public Long getListSizeForKey(String key) {
        return jedis.llen(key);
    }

}
