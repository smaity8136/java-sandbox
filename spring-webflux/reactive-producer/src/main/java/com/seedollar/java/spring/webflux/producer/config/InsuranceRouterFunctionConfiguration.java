package com.seedollar.java.spring.webflux.producer.config;

import com.seedollar.java.spring.webflux.producer.domain.InsuranceQuote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Configuration
@Slf4j
public class InsuranceRouterFunctionConfiguration {

    private static List<InsuranceQuote> QUOTES = List.of(
            InsuranceQuote.builder().classification("LOW").insuredValue(50000.00).premium(60.0).build(),
            InsuranceQuote.builder().classification("LOW").insuredValue(70000.00).premium(95.0).build(),
            InsuranceQuote.builder().classification("MEDIUM").insuredValue(100000.00).premium(122.64).build(),
            InsuranceQuote.builder().classification("MEDIUM").insuredValue(140000.00).premium(179.30).build(),
            InsuranceQuote.builder().classification("HIGH").insuredValue(200000.00).premium(243.55).build(),
            InsuranceQuote.builder().classification("PRICELESS").insuredValue(300000.00).premium(400.0).build());

    @Bean
    public RouterFunction<?> insuranceRoutes() {
        return RouterFunctions.route()
                .GET("/insurance/quote", request -> {
                    Double purchasePrice = request.queryParam("purchasePrice").map(val -> Double.parseDouble(val)).orElseThrow(() -> new IllegalArgumentException("Purchase price is invalid." + request.attribute("purchasePrice")));
                    return QUOTES.stream().filter(quote -> quote.getInsuredValue() > purchasePrice).map(quote -> ServerResponse.ok().syncBody(quote)).findFirst().orElseThrow(() -> new RuntimeException("Un-insurable, too expensive!"));
                })
                .filter((request, next) -> {
                    Duration duration = Duration.ofSeconds(3);
                    return Mono.delay(duration).flatMap(aLong -> next.handle(request));
                })
                .build();
    }

}
