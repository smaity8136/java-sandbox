package com.seedollar.sandbox.junit5.service;

import com.seedollar.sandbox.junit5.domain.Snack;
import io.vavr.control.Try;
import org.assertj.core.util.Lists;

import java.util.List;

public abstract class AbstractVendingMachine implements VendingMachine {

    protected List<Snack> snackList;

    public AbstractVendingMachine(Snack... snacks) {
        this.snackList = Lists.newArrayList(snacks);
    }

    @Override
    public Snack dispense(String itemID) {
        return Try.of(() -> snackList.stream().filter(snack -> snack.itemId().equalsIgnoreCase(itemID)).findFirst().get()).getOrElseThrow(throwable -> new RuntimeException(String.format("No snack item ID exists for: %s", itemID)));
    }

    public abstract boolean addSnack(Snack newSnack);
}
