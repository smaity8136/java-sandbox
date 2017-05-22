package com.seedollar.java.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Queue;

/**
 * Created by seedollar on 5/17/17.
 */
public class HazelcastMain {

    public static void main(String[] args) {
        Config hazelcastConfig = new Config();

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(hazelcastConfig);
        IMap<Integer, String> customers = hazelcastInstance.getMap("customers");
        customers.put(1, "Jimmy");
        customers.put(2, "Carl");
        customers.put(3, "Senzo");

        System.out.println("customer 1 = " + customers.get(1));
        System.out.println("customers size = " + customers.size());

        Queue<String> queueCustomers = hazelcastInstance.getQueue("customers");
        queueCustomers.offer("Tom");
        queueCustomers.offer("Mary");
        queueCustomers.offer("Jane");
        System.out.println("First customer: " + queueCustomers.poll());
        System.out.println("Second customer: "+ queueCustomers.peek());
        System.out.println("Queue size: " + queueCustomers.size());

    }
}
