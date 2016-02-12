package com.seedollar.java.springboot.configuration;

import com.seedollar.java.springboot.configuration.settings.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * Created by seedollar on 2016/02/11.
 */
@SpringBootApplication
public class SpringBootConfigurationPropertiesMain implements ApplicationRunner {

    @Autowired
    private ConfigSettings developerConfigSettings;

    @Autowired
    private ConfigSettings designerConfigSettings;

    @Autowired
    private ConfigSettings businessConfigSettings;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootConfigurationPropertiesMain.class);
        springApplication.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        springApplication.run(args);

        // TODO: execute the jar as follows: java -jar build/libs/springboot-configurationproperties-1.0.0-SNAPSHOT.jar --spring.config.name=myproperties --spring.config.location=classpath:/application.yml --spring.profiles.active=uat
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n=======================DEVELOPER PROFILE=======================");
        System.out.println("developer prefix designation = " + developerConfigSettings.getDesignation());
        System.out.println("developer prefix aliases = " + developerConfigSettings.getAliases());
        System.out.println("\n=======================DESIGNER PROFILE=======================");
        System.out.println("designer prefix designation = " + designerConfigSettings.getDesignation());
        System.out.println("designer prefix aliases = " + designerConfigSettings.getAliases());
        System.out.println("\n=======================BUSINESS PROFILE=======================");
        System.out.println("business prefix designation = " + businessConfigSettings.getDesignation());
        System.out.println("business prefix aliases = " + businessConfigSettings.getAliases());
        System.out.println("\n\n=======================[SPRING PROFILES: " + environment.getProperty("spring.profiles.active"));
        System.out.println("server.address = " + environment.getProperty("server.address"));
        System.out.println("server.port = " + environment.getProperty("server.port"));
    }
}
