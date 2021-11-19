package com.hemic.one.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import tech.jhipster.config.JHipsterConstants;

@Configuration
public class CacheConfiguration {

    @Bean
    @Profile({JHipsterConstants.SPRING_PROFILE_DEVELOPMENT,JHipsterConstants.SPRING_PROFILE_TEST})
    public CacheManager cacheManager(){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            //初始容量
            .initialCapacity(10)
            //用来控制cache的最大缓存数量
            .maximumSize(1000)
        );
        return cacheManager;
    }
}
