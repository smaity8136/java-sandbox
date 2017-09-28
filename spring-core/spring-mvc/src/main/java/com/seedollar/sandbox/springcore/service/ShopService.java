package com.seedollar.sandbox.springcore.service;

import com.seedollar.sandbox.springcore.domain.Weapon;

import java.util.List;

public interface ShopService {

    List<Weapon> getEntireInventory();
}
