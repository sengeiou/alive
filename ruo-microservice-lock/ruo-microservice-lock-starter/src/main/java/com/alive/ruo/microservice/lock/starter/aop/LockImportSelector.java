package com.alive.ruo.microservice.lock.starter.aop;

import com.alive.ruo.microservice.autoconfigure.selector.AbstractImportSelector;
import com.alive.ruo.microservice.autoconfigure.selector.RelaxedPropertyResolver;
import com.alive.ruo.microservice.lock.constant.LockConstant;
import com.alive.ruo.microservice.lock.starter.annotation.EnableLock;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.starter.aop
 * @title: LockImportSelector
 * @description: TODO
 * @date 2019/6/14 17:26
 */
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class LockImportSelector extends AbstractImportSelector<EnableLock> {

    @Override
    protected boolean isEnabled() {
        return new RelaxedPropertyResolver(getEnvironment()).getProperty(LockConstant.LOCK_ENABLED, Boolean.class, Boolean.TRUE);
    }

}