package com.seedollar.sandbox.moneyexchange.conversion.proxy;

import com.seedollar.sandbox.moneyexchange.conversion.dto.CurrencyConversionDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "forex-service")
@RibbonClient(name = "forex-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionDTO retrieveCurrencyPair(@PathVariable String from, @PathVariable String to);
}
