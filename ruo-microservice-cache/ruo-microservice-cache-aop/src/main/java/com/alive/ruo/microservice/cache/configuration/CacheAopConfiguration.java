package com.alive.ruo.microservice.cache.configuration;

import com.alive.ruo.microservice.cache.aop.CacheAutoScanProxy;
import com.alive.ruo.microservice.cache.aop.CacheInterceptor;
import com.alive.ruo.microservice.cache.constant.CacheConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.cache.configuration
 * @title: CacheAopConfiguration
 * @description: TODO
 * @date 2019/7/5 15:39
 */
@Configuration
public class CacheAopConfiguration {

    @Value("${" + CacheConstant.CACHE_SCAN_PACKAGES + ":}")
    private String scanPackages;

    @Bean
    public CacheAutoScanProxy cacheAutoScanProxy() {
        return new CacheAutoScanProxy(scanPackages);
    }

    @Bean
    public CacheInterceptor cacheInterceptor() {
        return new CacheInterceptor();
    }
}