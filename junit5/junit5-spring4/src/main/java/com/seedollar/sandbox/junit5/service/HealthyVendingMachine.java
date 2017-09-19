package com.seedollar.sandbox.junit5.service;

import com.seedollar.sandbox.junit5.domain.Fruit;
import com.seedollar.sandbox.junit5.domain.Snack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HealthyVendingMachine extends AbstractVendingMachine {

    public HealthyVendingMachine(Snack... snacks) {
        List<Snack> healthySnacks = Arrays.stream(snacks).filter(snack -> snack instanceof Fruit).collect(Collectors.toList());
        this.snackList = healthySnacks;
    }

    @Override
    public boolean addSnack(Snack newSnack) {
        if (newSnack instanceof Fruit) {
            snackList.add(newSnack);
            return true;
        }
        return false;
    }
}
