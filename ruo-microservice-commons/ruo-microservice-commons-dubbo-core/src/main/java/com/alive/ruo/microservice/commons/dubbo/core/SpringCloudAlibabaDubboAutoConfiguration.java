package com.alive.ruo.microservice.commons.dubbo.core;

import feign.Feign;
import org.apache.dubbo.config.AbstractConfig;
import org.apache.dubbo.config.spring.beans.factory.annotation.DubboFeignBuilder;
import org.apache.dubbo.config.spring.beans.factory.annotation.FeignClientToDubboProviderBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Set;

import static java.util.Collections.emptySet;
import static org.apache.dubbo.spring.boot.util.DubboUtils.BASE_PACKAGES_PROPERTY_NAME;
import static org.apache.dubbo.spring.boot.util.DubboUtils.DUBBO_PREFIX;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.dubbo.core
 * @title: SpringCloudAlibabaDubboAutoConfiguration
 * @description: TODO
 * @date 2019/3/18 11:22
 */
@Configuration
@ConditionalOnProperty(prefix = DUBBO_PREFIX, name = "enabled", matchIfMissing = true, havingValue = "true")
@ConditionalOnClass(AbstractConfig.class)
public class SpringCloudAlibabaDubboAutoConfiguration {

    @ConditionalOnProperty(name = BASE_PACKAGES_PROPERTY_NAME)
    @ConditionalOnClass(ConfigurationPropertySources.class)
    @Bean
    public FeignClientToDubboProviderBeanPostProcessor feignClientToDubboProviderBeanPostProcessor(Environment environment) {
        Set<String> packagesToScan = environment.getProperty(BASE_PACKAGES_PROPERTY_NAME, Set.class, emptySet());
        return new FeignClientToDubboProviderBeanPostProcessor(packagesToScan);
    }

    @Bean
    public Feign.Builder feignDubboBuilder() {
        return new DubboFeignBuilder();
    }
}