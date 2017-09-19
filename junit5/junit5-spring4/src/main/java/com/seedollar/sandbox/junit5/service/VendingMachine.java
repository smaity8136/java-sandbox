package com.seedollar.sandbox.junit5.service;

import com.seedollar.sandbox.junit5.domain.Snack;

public interface VendingMachine {

    Snack dispense(String itemID);

    boolean addSnack(Snack newSnack);
}
