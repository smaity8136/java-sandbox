package com.seedollar.sandbox.springcore.domain;

import com.seedollar.sandbox.springcore.annotation.African;
import com.seedollar.sandbox.springcore.annotation.Asian;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("starch")
@Asian
@African
public class Rice implements Ingredient {
}
