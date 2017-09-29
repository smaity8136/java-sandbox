package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiscountController {

    private ShopService shopService;

    public DiscountController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value = "/discounts", method = RequestMethod.GET)
    public String showDiscounted(@RequestParam("amount") Float amount, Model model) {
        model.addAttribute("discountedItems", shopService.getDiscounted(amount));
        return "shop/discounted";
    }
}
