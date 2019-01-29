package com.seedollar.sandbox.spring.web.rest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@XmlRootElement
public class Pet {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    private Date dateOfBirth;

    @Getter
    @Setter
    private Boolean fixed;

    public Pet() {
    }

    public Pet(String name, Integer age) {
        if (id == null) {
            id = ThreadLocalRandom.current().nextLong();
        }
        this.name = name;
        this.age = age;
    }
}


