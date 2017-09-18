package com.seedollar.sandbox.springcore;

import com.seedollar.sandbox.springcore.configuration.EmbeddedDatabaseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class EmbeddedDatabaseBuilderSpringApplication implements ApplicationRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{EmbeddedDatabaseBuilderConfiguration.class, EmbeddedDatabaseBuilderSpringApplication.class}, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jdbcTemplate.update("INSERT INTO anime (id, name, startDate, completed) VALUES (?, ?, ?, ?)", ThreadLocalRandom.current().nextLong(), "Gintama", LocalDateTime.now(), true);
        jdbcTemplate.update("INSERT INTO anime (id, name, startDate, completed) VALUES (?, ?, ?, ?)", ThreadLocalRandom.current().nextLong(), "Naruto", LocalDateTime.now(), true);
        jdbcTemplate.update("INSERT INTO anime (id, name, startDate, completed) VALUES (?, ?, ?, ?)", ThreadLocalRandom.current().nextLong(), "Dragonball Z", LocalDateTime.now(), true);

        Long rowCount = jdbcTemplate.queryForObject("select count(1) from anime", Long.class);

        System.out.println("rowCount = " + rowCount);
    }
}
