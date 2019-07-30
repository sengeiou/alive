package com.alive.ruo.microservice.commons.redisson.adapter;

import com.alive.ruo.microservice.commons.redisson.handler.RedissonHandler;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.redisson.adapter
 * @title: RedissonAdapter
 * @description: TODO
 * @date 2019/6/14 11:23
 */
public interface RedissonAdapter {

    RedissonHandler getRedissonHandler();

}