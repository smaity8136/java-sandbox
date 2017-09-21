package com.seedollar.sandbox.springcore.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("sweet")
public class Pipeapple implements Ingredient {
}
