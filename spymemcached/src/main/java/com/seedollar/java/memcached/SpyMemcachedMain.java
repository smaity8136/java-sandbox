package com.seedollar.java.memcached;

import com.seedollar.java.memcached.client.SpyMemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by seedollar on 4/8/16.
 */
@SpringBootApplication
public class SpyMemcachedMain implements ApplicationRunner {

    @Autowired
    SpyMemcachedClient spyMemcachedClient;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpyMemcachedMain.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        spyMemcachedClient.setupCache();
        spyMemcachedClient.findCache("foo");
        System.exit(0);
    }
}
