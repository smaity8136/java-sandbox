package za.co.seedollar.java.email.templating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Created by seedollar on 5/4/16.
 */
@SpringBootApplication
public class EmailTemplatingApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EmailTemplatingApplication.class);
        application.setApplicationContextClass(AnnotationConfigWebApplicationContext.class);
        application.run(EmailTemplatingApplication.class, args);
    }
}
