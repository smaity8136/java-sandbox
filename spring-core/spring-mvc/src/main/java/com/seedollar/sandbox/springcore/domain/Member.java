package com.seedollar.sandbox.springcore.domain;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class Member {

    private Long id;

    @NotNull
    @Size(min = 5, max = 20)
    private String username;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;

    @NotNull
    private String email;

    @NotNull
    @Future(message = "The registration date must be in the future!!!")
    private Date registrationDate;

    @NotNull
    @Past(message = "Birth Date cannot be in the future!!!")
    private Date birthDate;

    @AssertTrue
    private boolean active;

    @AssertFalse
    private boolean blacklisted;

    @NotNull
    @DecimalMax("5.00")
    @DecimalMin("2.50")
    private BigDecimal riskWeighting;

    @Max(90)
    @Min(18)
    @Digits(integer = 2, fraction = 0)
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    public BigDecimal getRiskWeighting() {
        return riskWeighting;
    }

    public void setRiskWeighting(BigDecimal riskWeighting) {
        this.riskWeighting = riskWeighting;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
