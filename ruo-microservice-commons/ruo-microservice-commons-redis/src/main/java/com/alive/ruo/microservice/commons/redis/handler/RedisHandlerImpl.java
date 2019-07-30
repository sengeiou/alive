package com.alive.ruo.microservice.commons.redis.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.redis.handler
 * @title: RedisHandlerImpl
 * @description: TODO
 * @date 2019/7/5 16:51
 */
public class RedisHandlerImpl implements RedisHandler {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取RedisTemplate
     * @return
     */
    @Override
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }
}