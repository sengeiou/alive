package com.alive.ruo.microservice.commons.redis.handler;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.redis.handler
 * @title: RedisHandler
 * @description: TODO
 * @date 2019/7/5 16:43
 */
public interface RedisHandler {
    /**
     * 获取RedisTemplate
     * @return
     */
    RedisTemplate<String, Object> getRedisTemplate();
}