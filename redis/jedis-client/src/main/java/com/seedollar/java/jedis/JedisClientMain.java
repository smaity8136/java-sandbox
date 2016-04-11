package com.seedollar.java.jedis;

import com.seedollar.java.jedis.service.JedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

/**
 * Created by seedollar on 4/11/16.
 */
@SpringBootApplication
public class JedisClientMain implements ApplicationRunner {

    @Autowired
    private JedisService jedisService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(JedisClientMain.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        final String BEST_FRIEND_KEY = "BEST_FRIEND";
        jedisService.insertKeyValue(BEST_FRIEND_KEY, "Reggie");
        String valueForBestFriendKey = jedisService.getValueForKey(BEST_FRIEND_KEY);
        System.out.println("valueForKey = " + valueForBestFriendKey);
        Assert.isTrue(valueForBestFriendKey.equalsIgnoreCase("Reggie"));

        // Insert a list of friends
        final String FRIENDS_KEY = "FRIENDS";
        jedisService.addToListAtStart(FRIENDS_KEY, "Damian");
        jedisService.addToListAtStart(FRIENDS_KEY, "Cyril");
        jedisService.addToListAtStart(FRIENDS_KEY, "Matthew");
        jedisService.addToListAtStart(FRIENDS_KEY, "Justin");
        jedisService.addToListAtStart(FRIENDS_KEY, "Freddy");

        jedisService.addToListAtEnd(FRIENDS_KEY, "Sarah");
        jedisService.addToListAtEnd(FRIENDS_KEY, "Nicky");
        jedisService.addToListAtEnd(FRIENDS_KEY, "Sebastian");

        System.out.println("FRIENDS_KEY = " + jedisService.getFullListForKey(FRIENDS_KEY));
        System.out.println("FRIENDS_KEY SIZE = " + jedisService.getListSizeForKey(FRIENDS_KEY));

    }
}
