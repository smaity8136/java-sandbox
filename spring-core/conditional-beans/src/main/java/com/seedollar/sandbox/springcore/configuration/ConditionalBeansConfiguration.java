package com.seedollar.sandbox.springcore.configuration;

import com.seedollar.sandbox.springcore.condition.StateCaptureCondition;
import com.seedollar.sandbox.springcore.domain.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class ConditionalBeansConfiguration {

    @Bean("capturedEmployee")
    @Conditional(StateCaptureCondition.class)
    public Employee capturedEmployee() {
        Employee capturedEmployee = new Employee("GUPTA" + ThreadLocalRandom.current().nextInt());
        capturedEmployee.setSalary(capturedEmployee.getSalary() * 10);
        return capturedEmployee;
    }

    @Bean("normalEmployee")
    public Employee normalEmployee() {
        Employee capturedEmployee = new Employee("PLEB" + ThreadLocalRandom.current().nextInt());
        return capturedEmployee;
    }

}
