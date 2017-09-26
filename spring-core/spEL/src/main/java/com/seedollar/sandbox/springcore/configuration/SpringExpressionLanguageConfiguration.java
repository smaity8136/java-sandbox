package com.seedollar.sandbox.springcore.configuration;

import com.seedollar.sandbox.springcore.domain.Role;
import com.seedollar.sandbox.springcore.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.UUID;

@Configuration
@PropertySource(value = "classpath:spEL.properties")
public class SpringExpressionLanguageConfiguration {

    @Bean
    public User spELUser() {
        User spELUser = new User(UUID.randomUUID().toString(), "spELUser", true, "spEL is boss!!!!");
        spELUser.getTags()[0] = 9794;
        spELUser.getTags()[2] = 4982;
        spELUser.getTags()[4] = 82233;
        spELUser.getTags()[7] = 2175721;
        spELUser.getTags()[9] = 5972;

        spELUser.getRoles().add(new Role("admin", 4));
        spELUser.getRoles().add(new Role("normal", 2));
        spELUser.getRoles().add(new Role("supervisor", 2));
        spELUser.getRoles().add(new Role("finance",7));

        return spELUser;
    }
}
