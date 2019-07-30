package com.alive.ruo.microservice.lock.zookeeper.configuration;

import com.alive.ruo.microservice.commons.base.constant.GlobalConstant;
import com.alive.ruo.microservice.commons.zookeeper.handler.CuratorHandler;
import com.alive.ruo.microservice.commons.zookeeper.handler.CuratorHandlerImpl;
import com.alive.ruo.microservice.lock.LockDelegate;
import com.alive.ruo.microservice.lock.LockExecutor;
import com.alive.ruo.microservice.lock.zookeeper.condition.ZookeeperLockCondition;
import com.alive.ruo.microservice.lock.zookeeper.impl.ZookeeperLockDelegateImpl;
import com.alive.ruo.microservice.lock.zookeeper.impl.ZookeeperLockExecutorImpl;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.zookeeper.configuration
 * @title: ZookeeperLockConfiguration
 * @description: TODO
 * @date 2019/7/30 14:43
 */
@Configuration
public class ZookeeperLockConfiguration {
    @Value("${" + GlobalConstant.PREFIX + "}")
    private String prefix;

    @Bean
    @Conditional(ZookeeperLockCondition.class)
    public LockDelegate zookeeperLockDelegate() {
        return new ZookeeperLockDelegateImpl();
    }

    @Bean
    @Conditional(ZookeeperLockCondition.class)
    public LockExecutor<InterProcessMutex> zookeeperLockExecutor() {
        return new ZookeeperLockExecutorImpl();
    }

    @Bean
    @Conditional(ZookeeperLockCondition.class)
    public CuratorHandler curatorHandler() {
        return new CuratorHandlerImpl(prefix);
    }
}