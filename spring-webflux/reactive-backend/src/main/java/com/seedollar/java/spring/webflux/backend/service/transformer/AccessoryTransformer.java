package com.seedollar.java.spring.webflux.backend.service.transformer;

import com.seedollar.java.spring.webflux.backend.common.util.Transformer1;
import com.seedollar.java.spring.webflux.backend.domain.Accessory;
import com.seedollar.java.spring.webflux.backend.domain.dto.AccessoryResponse;
import org.springframework.stereotype.Component;

@Component
public class AccessoryTransformer implements Transformer1<AccessoryResponse, Accessory> {

    @Override
    public Accessory apply(AccessoryResponse accessoryResponse) {
        return Accessory.builder()
                .cost(accessoryResponse.getCost())
                .description(accessoryResponse.getDescription())
                .build();
    }
}
