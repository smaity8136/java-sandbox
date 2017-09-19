package com.seedollar.sandbox.junit5.service;

import com.seedollar.sandbox.junit5.domain.Snack;

public class GeneralVendingMachine extends AbstractVendingMachine {

    public GeneralVendingMachine(Snack... snacks) {
        super(snacks);
    }

    @Override
    public boolean addSnack(Snack newSnack) {
        snackList.add(newSnack);
        return true;
    }
}
