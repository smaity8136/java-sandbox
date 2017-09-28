package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String shop(Model model) {
        model.addAttribute("inventory", shopService.getEntireInventory());
        return "shop/landing";
    }
}
