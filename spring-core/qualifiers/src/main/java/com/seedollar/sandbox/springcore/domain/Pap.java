package com.seedollar.sandbox.springcore.domain;

import com.seedollar.sandbox.springcore.annotation.African;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("starch")
@African
public class Pap implements Ingredient {
}
