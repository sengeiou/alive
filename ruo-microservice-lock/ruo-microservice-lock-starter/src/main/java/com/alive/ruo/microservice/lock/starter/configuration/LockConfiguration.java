package com.alive.ruo.microservice.lock.starter.configuration;

import com.alive.ruo.microservice.lock.configuration.LockAopConfiguration;
import com.alive.ruo.microservice.lock.local.configuration.LocalLockConfiguration;
import com.alive.ruo.microservice.lock.redis.configuration.RedisLockConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.starter.configuration
 * @title: LockConfiguration
 * @description: TODO
 * @date 2019/6/14 17:27
 */
@Configuration
@Import({ LockAopConfiguration.class, RedisLockConfiguration.class, LocalLockConfiguration.class/*, ZookeeperLockConfiguration.class*/ })
public class LockConfiguration {

}