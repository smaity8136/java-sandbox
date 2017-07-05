package za.co.seedollar.sandbox.springloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MessageConverter;
import za.co.seedollar.sandbox.springloud.converter.PetMessageConverter;

/**
 * Created by seedollar on 7/5/17.
 */
@SpringBootApplication
public class SpringCloudStreamMessageConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamMessageConverterApplication.class, args);
    }

    @Bean
    public MessageConverter petMessageConverter() {
        return new PetMessageConverter();
    }
}
