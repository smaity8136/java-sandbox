package com.seedollar.sandbox.spring.web.rest.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@XmlRootElement
public class Pet {

    private Long id;
    private String name;
    private Integer age;
    private Date dateOfBirth;
    private Boolean fixed;

    public Pet() {}

    public Pet(String name, Integer age) {
        if (id == null) {
            id = ThreadLocalRandom.current().nextLong();
        }
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean isFixed() {
        return fixed;
    }

    public void setFixed(Boolean fixed) {
        this.fixed = fixed;
    }
}
