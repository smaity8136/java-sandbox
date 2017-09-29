package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.domain.Weapon;
import com.seedollar.sandbox.springcore.service.ShopService;
import io.vavr.Function6;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ShopControllerTest {

    static Function6<Long, String, String, Integer, Float, Boolean, Weapon> createMockWeaponFunction = (id, name, description, damage, price, inStock) -> {
        Weapon weapon = new Weapon();
        weapon.setId(id);
        weapon.setName(name);
        weapon.setDescription(description);
        weapon.setDamageIndicator(damage);
        weapon.setPrice(price);
        weapon.setInStock(inStock);
        return weapon;
    };

    @Mock
    ShopService shopService;

    @Before
    public void beforeAll() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowEntireInventory() throws Exception {
        List<Weapon> mockWeapons = IntStream.range(1, new Random(10).nextInt() + 1)
                .mapToObj(num -> createMockWeaponFunction.apply(Long.valueOf(num), "weapon" + num, "description" + num, num, Float.valueOf(num * 2), true)).collect(Collectors.toList());

        when(shopService.getEntireInventory()).thenReturn(mockWeapons);

        ShopController shopController = new ShopController(shopService);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(shopController)
                .setSingleView(new InternalResourceView("WEB-INF/views/shop/landing.jsp")).build();

        mockMvc.perform(get("/shop"))
                .andExpect(view().name("shop/landing"))
                .andExpect(model().attributeExists("inventory"))
                .andExpect(model().attribute("inventory", hasItems(mockWeapons.toArray())));
    }
}
