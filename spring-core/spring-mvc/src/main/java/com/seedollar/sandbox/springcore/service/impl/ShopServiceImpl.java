package com.seedollar.sandbox.springcore.service.impl;

import com.seedollar.sandbox.springcore.domain.Weapon;
import com.seedollar.sandbox.springcore.repository.WeaponRepository;
import com.seedollar.sandbox.springcore.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private WeaponRepository weaponRepository;

    public ShopServiceImpl(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public List<Weapon> getEntireInventory() {
        return weaponRepository.getAll();
    }
}
