package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.domain.Judge;
import com.seedollar.sandbox.springcore.domain.MP;
import com.seedollar.sandbox.springcore.domain.Minister;
import com.seedollar.sandbox.springcore.domain.President;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regional")
public class RegionalController {

    private President president;

    @Autowired
    private MP memberParliament;

    @Autowired
    private Minister minister;

    @Autowired
    private Judge judge;

    public RegionalController(President president) {
        this.president = president;
    }

    @GetMapping("/president")
    public String getPresident() {
        return president.getIdentifier();
    }

    @GetMapping("/mp")
    public String getMP() {
        return memberParliament.getIdentifier();
    }

    @GetMapping("/minister")
    public String getMinister() {
        return minister.getIdentifier();
    }

    @GetMapping("/judge")
    public String judge() {
        return judge.getIdentifier();
    }

}
