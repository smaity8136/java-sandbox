package com.seedollar.java.sandbox.resilience4j.config;

import io.github.resilience4j.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;


@Configuration
public class CacheConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CacheConfiguration.class);

    @Bean
    public CachingProvider cacheProvider() {
        return Caching.getCachingProvider();
    }

    @Bean
    public CacheManager cacheManager() {
        return cacheProvider().getCacheManager();
    }

    @Bean
    public Cache branchCacheContext() {
        // Create an JCache implementation
        javax.cache.Cache branchCache = cacheManager().createCache("branchCache", new MutableConfiguration());
        // Wrap the cache implementation in a CacheContext
        Cache branchCacheContext = Cache.of(branchCache);
        branchCacheContext.getEventPublisher()
                .onCacheHit(evt -> logger.info("Cache Hit - Event = {}, CacheKey = {}", evt.getEventType().name(), evt.getCacheKey()))
                .onCacheMiss(evt -> logger.info("Cache Miss - Event = {}, CacheKey = {}", evt.getEventType().name(), evt.getCacheKey()));
        return branchCacheContext;
    }
}
