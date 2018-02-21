package com.seedollar.sanbox.moneyexchange.forex.controller;

import com.seedollar.sanbox.moneyexchange.forex.domain.ExchangePair;
import com.seedollar.sanbox.moneyexchange.forex.repository.ExchangePairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ForexController {

    @Autowired
    Environment environment;

    @Autowired
    ExchangePairRepository exchangePairRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangePair retrieveExchangePair(@PathVariable String from, @PathVariable String to) {
        ExchangePair exchangePair = exchangePairRepository.findByFromAndTo(from, to);
        exchangePair.setPort(Integer.parseInt(environment.getProperty("server.port")));
        return exchangePair;
    }

    @GetMapping("/currency-exchange/pairs")
    public List<ExchangePair> retrieveExchangePairs() {
        return exchangePairRepository.findAll();
    }
}
