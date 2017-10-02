package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showEntireInventory(Model model) {
        model.addAttribute("inventory", shopService.getEntireInventory());
        return "shop/landing";
    }

    /**
     * Example to illustrate @RequestParam annotation
     * @param amount
     * @param model
     * @return
     */
    @RequestMapping(value = "/discounts", method = RequestMethod.GET)
    public String showDiscounted(@RequestParam("amount") Float amount, Model model) {
        model.addAttribute("discountedItems", shopService.getDiscounted(amount));
        return "shop/discounted";
    }

    /**
     * Example to illustrate @PathVariable annotation
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/{itemId}", method = RequestMethod.GET)
    public String viewItem(@PathVariable("itemId") Long itemId, Model model) {
        model.addAttribute("targetItem", shopService.getItemForId(itemId));
        return "shop/item";
    }

    /**
     * Example to illustrate @PathVariable annotation
     * @param inStock
     * @param model
     * @return
     */
    @RequestMapping(value = "/stocks/{inStock}", method = RequestMethod.GET)
    public String getForStockIndicator(@PathVariable("inStock") boolean inStock, Model model) {
        model.addAttribute("stockItems", shopService.getInStock(inStock));
        return "shop/stock";
    }


}
