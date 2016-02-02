package com.seedollar.java.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationListener;

/**
 * Created by seedollar on 2016/02/02.
 */
@SpringBootApplication
public class SpringBootEventsListenersMain {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootEventsListenersMain.class);

        ApplicationListener<ApplicationStartedEvent> startedEventApplicationListener = new ApplicationListener<ApplicationStartedEvent>() {
            @Override
            public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
                System.out.println("APPLICATION_STARTED_EVENT /////////////////////////////////////////////");
            }
        };

        ApplicationListener<ApplicationEnvironmentPreparedEvent> environmentPreparedEventListener = new ApplicationListener<ApplicationEnvironmentPreparedEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
                System.out.println("\nAPPLICATION_ENVIRONMENT_PREPARED_EVENT //////////////////////////////////////////////////");
            }
        };

        ApplicationListener<ApplicationPreparedEvent> preparedEventApplicationListener = new ApplicationListener<ApplicationPreparedEvent>() {
            @Override
            public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
                System.out.println("\nAPPLICATION_PREPARED_EVENT /////////////////////////////////////////////////////");
            }
        };

        ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener = new ApplicationListener<ApplicationReadyEvent>() {
            @Override
            public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
                System.out.println("APPLICATION_READY_EVENT ////////////////////////////////////////////////");
            }
        };

        ApplicationListener<ApplicationFailedEvent> failedEventApplicationListener = new ApplicationListener<ApplicationFailedEvent>() {
            @Override
            public void onApplicationEvent(ApplicationFailedEvent applicationFailedEvent) {
                System.out.println("APPLICATION_FAILED_EVENT //////////////////////////////////////////////////");
            }
        };


        springApplication.addListeners(startedEventApplicationListener, environmentPreparedEventListener, preparedEventApplicationListener, readyEventApplicationListener, failedEventApplicationListener);
        springApplication.run(args);
    }
}
