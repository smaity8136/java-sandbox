package com.seedollar.sandbox.springcore.domain;

import com.seedollar.sandbox.springcore.annotation.Cold;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Cold
@Qualifier("sweet")
public class IceCream implements Ingredient {
}
