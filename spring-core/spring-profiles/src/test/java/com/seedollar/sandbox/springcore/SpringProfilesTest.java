package com.seedollar.sandbox.springcore;

import com.seedollar.sandbox.springcore.configuration.*;
import com.seedollar.sandbox.springcore.domain.Biller;
import com.seedollar.sandbox.springcore.domain.Container;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringProfilesConfiguration.class, DevProfileConfiguration.class, UatProfileConfiguration.class, PerfProfileConfiguration.class, ProdProfileConfiguration.class})
@ActiveProfiles("perf")
public class SpringProfilesTest {

    @Autowired
    Biller biller;

    @Autowired
    Container container;

    @Test
    public void testActiveProfiles() {
        System.out.println("biller = " + biller.getClass().getName());
        System.out.println("billable amount = " + biller.billableAmount());
        System.out.println(String.format("container = %s (%d)", container.getName(), container.getThreadCount()));

        Assertions.assertEquals("PerfBiller", biller.getClass().getSimpleName());
        Assertions.assertTrue(biller.billableAmount() < 395d);
        Assertions.assertEquals("TOMCAT", container.getName());
        Assertions.assertEquals(5, container.getThreadCount().intValue());
    }
}
