package com.seedollar.java.springboot.banner;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * Created by seedollar on 2016/02/02.
 */
public class MyBanner implements Banner {

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        StringBuilder bannerBuilder = new StringBuilder("===================================================================================\n");
        bannerBuilder.append("===================================================================================\n");
        bannerBuilder.append("My Custom Spring Banner\n");
        bannerBuilder.append("===================================================================================\n");
        bannerBuilder.append("===================================================================================\n");
        System.out.println(bannerBuilder.toString());
    }
}
