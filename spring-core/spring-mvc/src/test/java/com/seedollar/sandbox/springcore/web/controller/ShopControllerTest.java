package com.seedollar.sandbox.springcore.web.controller;

import com.google.common.collect.Lists;
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
import static org.mockito.Mockito.any;
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

    @Test
    public void testShowDiscountedInventory() throws Exception {
        List<Weapon> discountedWeapons = Lists.newArrayList(
                createMockWeaponFunction.apply(1l, "weapon1", "description1", 1, 1000f, true),
                createMockWeaponFunction.apply(2l, "weapon2", "description2", 1, 1100f, true),
                createMockWeaponFunction.apply(3l, "weapon3", "description3", 4, 2000f, false),
                createMockWeaponFunction.apply(4l, "weapon4", "description4", 1, 1470f, true),
                createMockWeaponFunction.apply(5l, "weapon5", "description5", 3, 1600f, false),
                createMockWeaponFunction.apply(6l, "weapon6", "description6", 5, 640f, true),
                createMockWeaponFunction.apply(7l, "weapon7", "description7", 1, 400f, true)
        );

        when(shopService.getDiscounted(any())).thenReturn(discountedWeapons);

        ShopController shopController = new ShopController(shopService);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(shopController).setSingleView(new InternalResourceView("/WEB-INF/view/shop/discounted.jsp")).build();

        mockMvc.perform(get("/shop/discounts?amount=866"))
                .andExpect(view().name("shop/discounted"))
                .andExpect(model().attributeExists("discountedItems"))
                .andExpect(model().attribute("discountedItems", hasItems(discountedWeapons.toArray())));
    }

    @Test
    public void testViewItem() throws Exception {
        Weapon targetWeapon = createMockWeaponFunction.apply(943l, "Silencer", "Whisper Slaughter", 2, 1600F, true);

        when(shopService.getItemForId(any())).thenReturn(targetWeapon);

        ShopController shopController = new ShopController(shopService);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(shopController).build();

        mockMvc.perform(get("/shop/view/943"))
                .andExpect(view().name("shop/item"))
                .andExpect(model().attributeExists("targetItem"))
                .andExpect(model().attribute("targetItem", targetWeapon));
    }

    @Test
    public void testInStockItems() throws Exception {
        List<Weapon> inStockWeapons = IntStream.range(1, 5).mapToObj(val ->
                createMockWeaponFunction.apply(new Long(val), "weapon" + val, "description" + val, val, new Float(val * 2), true)
        ).collect(Collectors.toList());

        when(shopService.getInStock(true)).thenReturn(inStockWeapons);

        ShopController shopController = new ShopController(shopService);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(shopController).build();
        mockMvc.perform(get("/shop/stocks/true"))
                .andExpect(view().name("shop/stock"))
                .andExpect(model().attributeExists("stockItems"))
                .andExpect(model().attribute("stockItems", hasItems(inStockWeapons.toArray())));

    }
}
