package com.alive.ruo.microservice.cache.starter.aop;

import com.alive.ruo.microservice.autoconfigure.selector.AbstractImportSelector;
import com.alive.ruo.microservice.autoconfigure.selector.RelaxedPropertyResolver;
import com.alive.ruo.microservice.cache.starter.annotation.EnableCache;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.cache.starter.aop
 * @title: CacheImportSelector
 * @description: TODO
 * @date 2019/7/5 16:58
 */
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class CacheImportSelector extends AbstractImportSelector<EnableCache> {

    @Override
    protected boolean isEnabled() {
        return new RelaxedPropertyResolver(getEnvironment()).getProperty("cache.enabled", Boolean.class, Boolean.TRUE);
    }
}
