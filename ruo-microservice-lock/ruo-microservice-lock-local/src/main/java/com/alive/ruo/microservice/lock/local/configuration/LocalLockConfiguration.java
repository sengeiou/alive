package com.alive.ruo.microservice.lock.local.configuration;

import com.alive.ruo.microservice.lock.LockDelegate;
import com.alive.ruo.microservice.lock.LockExecutor;
import com.alive.ruo.microservice.lock.local.condition.LocalLockCondition;
import com.alive.ruo.microservice.lock.local.impl.LocalLockDelegateImpl;
import com.alive.ruo.microservice.lock.local.impl.LocalLockExecutorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.locks.Lock;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.local.configuration
 * @title: LocalLockConfiguration
 * @description: TODO
 * @date 2019/6/14 18:03
 */
@Configuration
public class LocalLockConfiguration {

    @Bean
    @Conditional(LocalLockCondition.class)
    public LockDelegate localLockDelegate() {
        return new LocalLockDelegateImpl();
    }

    @Bean
    @Conditional(LocalLockCondition.class)
    public LockExecutor<Lock> localLockExecutor() {
        return new LocalLockExecutorImpl();
    }
}
