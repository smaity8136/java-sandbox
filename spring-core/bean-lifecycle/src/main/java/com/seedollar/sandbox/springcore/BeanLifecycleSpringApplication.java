package com.seedollar.sandbox.springcore;

import com.seedollar.sandbox.springcore.configuration.BeanLifecycleConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by seedollar on 8/16/17.
 */
public class BeanLifecycleSpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanLifecycleConfiguration.class);
        annotationConfigApplicationContext.destroy(); // Should explicitly call destroy else the DispoableBean Aware interface is not invoked.

    }
}
