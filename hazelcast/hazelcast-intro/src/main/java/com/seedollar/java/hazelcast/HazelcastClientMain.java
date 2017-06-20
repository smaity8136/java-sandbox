package com.seedollar.java.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * Created by seedollar on 5/17/17.
 */
public class HazelcastClientMain {

    public static void main(String[] args) {
        ClientConfig hazelcastConfig = new ClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(hazelcastConfig);
        IMap<Integer, String> customers = client.getMap("customers");
        System.out.println("customers size = " + customers.size());
    }
}
