package com.seedollar.sandbox.springcore.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by seedollar on 8/16/17.
 */
public class SpringBeanPojo implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {

    private AtomicInteger awareLifecycleCounter;
    private LocalDateTime created;

    public SpringBeanPojo() {
        awareLifecycleCounter = new AtomicInteger(0);
        created = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(String.format("Aware Lifecycle Counter: [%d] -> Bean Name Aware = %s", awareLifecycleCounter.incrementAndGet(), name));
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(String.format("Aware Lifecycle Counter: [%s] -> Bean Factory Aware = %s", awareLifecycleCounter.incrementAndGet(), beanFactory.toString()));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(String.format("Aware Lifecycle Counter: [%s] -> Bean Application Aware = %s", awareLifecycleCounter.incrementAndGet(), applicationContext.getId()));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(String.format("Aware Lifecycle Counter: [%s] -> BeanPostProcessor - postProcessBeforeInitialization() = %s", awareLifecycleCounter.incrementAndGet(), beanName));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(String.format("Aware Lifecycle Counter: [%s] -> BeanPostProcessor - postProcessAfterInitialization() = %s", awareLifecycleCounter.incrementAndGet(), beanName));
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(String.format("Aware Lifecycle Counter: [%s] -> InitializingBean - afterPropertiesSet()", awareLifecycleCounter.incrementAndGet()));
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(String.format("Aware Lifecycle Counter: [%s] -> DisposableBean - destroy()", awareLifecycleCounter.incrementAndGet()));
    }
}
