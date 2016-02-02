package com.seedollar.java.springboot;

import com.seedollar.java.springboot.banner.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by seedollar on 2016/02/02.
 */
@SpringBootApplication
public class SpringBootBannerMain {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootBannerMain.class);
        springApplication.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        springApplication.setBanner(new MyBanner());
//        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

}
