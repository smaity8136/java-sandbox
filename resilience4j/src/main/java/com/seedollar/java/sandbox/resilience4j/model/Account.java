package com.seedollar.java.sandbox.resilience4j.model;

import com.seedollar.java.sandbox.resilience4j.model.enumeration.AccountTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Account {

    private String accountId;

    private LocalDate createdDate;

    private AccountTypeEnum accountType;


}
