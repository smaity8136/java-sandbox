package com.seedollar.sandbox.springcore.service;

import com.seedollar.sandbox.springcore.domain.Weapon;

import java.util.List;

public interface ShopService {

    List<Weapon> getEntireInventory();

    List<Weapon> getDiscounted(Float amount);

    Weapon getItemForId(Long itemId);

    List<Weapon> getInStock(boolean inStock);
}
