package com.seedollar.java.spring.webflux.backend.service.impl;

import com.seedollar.java.spring.webflux.backend.common.util.Transformer1;
import com.seedollar.java.spring.webflux.backend.consumer.AccessoryConsumer;
import com.seedollar.java.spring.webflux.backend.domain.Accessory;
import com.seedollar.java.spring.webflux.backend.domain.dto.AccessoryResponse;
import com.seedollar.java.spring.webflux.backend.service.AccessoryService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryConsumer accessoryConsumer;
    private final Transformer1<AccessoryResponse, Accessory> accessoryTransformer;

    public AccessoryServiceImpl(AccessoryConsumer accessoryConsumer, Transformer1<AccessoryResponse, Accessory> accessoryTransformer) {
        this.accessoryConsumer = accessoryConsumer;
        this.accessoryTransformer = accessoryTransformer;
    }

    @Override
    public Mono<Accessory> getAccessory(long accessoryId) {
        return accessoryConsumer.getAccessory(accessoryId).map(accessoryTransformer);
    }

    @Override
    public Flux<Accessory> getAccessories() {
        return accessoryConsumer.getAccessories().map(accessoryTransformer);
    }
}
