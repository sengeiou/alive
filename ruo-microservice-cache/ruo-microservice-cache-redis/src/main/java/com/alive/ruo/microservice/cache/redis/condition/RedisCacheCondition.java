package com.alive.ruo.microservice.cache.redis.condition;

import com.alive.ruo.microservice.cache.condition.CacheCondition;
import com.alive.ruo.microservice.cache.constant.CacheConstant;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.cache.redis.condition
 * @title: RedisCacheCondition
 * @description: TODO
 * @date 2019/7/5 15:57
 */
public class RedisCacheCondition extends CacheCondition {
    public RedisCacheCondition() {
        super(CacheConstant.CACHE_TYPE, CacheConstant.CACHE_TYPE_REDIS);
    }
}