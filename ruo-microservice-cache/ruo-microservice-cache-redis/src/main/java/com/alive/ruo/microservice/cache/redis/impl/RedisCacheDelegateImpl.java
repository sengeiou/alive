package com.alive.ruo.microservice.cache.redis.impl;

import com.alive.ruo.microservice.cache.constant.CacheConstant;
import com.alive.ruo.microservice.cache.util.KeyUtil;
import com.alive.ruo.microservice.commons.redis.handler.RedisHandler;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.alive.ruo.microservice.cache.CacheDelegate;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.cache.redis.impl
 * @title: RedisCacheDelegateImpl
 * @description: TODO
 * @date 2019/7/5 16:00
 */
@Slf4j
public class RedisCacheDelegateImpl implements CacheDelegate {

    @Autowired
    private RedisHandler redisHandler;

    @Value("${" + CacheConstant.PREFIX + "}")
    private String prefix;

    @Value("${" + CacheConstant.FREQUENT_LOG_PRINT + ":false}")
    private Boolean frequentLogPrint;

    @Override
    public Object invokeCacheable(MethodInvocation invocation, String key, long expire) throws Throwable {
        RedisTemplate<String, Object> redisTemplate = redisHandler.getRedisTemplate();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        // 空值不缓存
        Object object = null;
        try {
            object = valueOperations.get(key);
        } catch (Exception e) {
            log.warn("Redis exception occurs while getting data", e);
        }

        if (frequentLogPrint) {
            log.info("Before invocation, Cacheable key={}, cache={} in Redis", key, object);
        }

        if (object != null) {
            return object;
        }

        object = invocation.proceed();

        if (object != null) {
            try {
                if (expire == -1) {
                    valueOperations.set(key, object);
                } else {
                    valueOperations.set(key, object, expire, TimeUnit.MILLISECONDS);
                }
            } catch (Exception e) {
                log.warn("Redis exception occurs while setting data", e);
            }

            if (frequentLogPrint) {
                log.info("After invocation, Cacheable key={}, cache={} in Redis", key, object);
            }
        }

        return object;
    }

    @Override
    public Object invokeCachePut(MethodInvocation invocation, String key, long expire) throws Throwable {
        RedisTemplate<String, Object> redisTemplate = redisHandler.getRedisTemplate();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        // 空值不缓存
        Object object = invocation.proceed();
        if (object != null) {
            try {
                if (expire == -1) {
                    valueOperations.set(key, object);
                } else {
                    valueOperations.set(key, object, expire, TimeUnit.MILLISECONDS);
                }
            } catch (Exception e) {
                log.warn("Redis exception occurs while setting data", e);
            }

            if (frequentLogPrint) {
                log.info("After invocation, CachePut key={}, cache={} in Redis", key, object);
            }
        }

        return object;
    }

    @Override
    public Object invokeCacheEvict(MethodInvocation invocation, String key, String name, boolean allEntries, boolean beforeInvocation) throws Throwable {
        if (beforeInvocation) {
            if (frequentLogPrint) {
                log.info("Before invocation, CacheEvict clear key={} in Redis", key);
            }

            try {
                clear(key, name, allEntries);
            } catch (Exception e) {
                log.warn("Redis exception occurs while setting data", e);
            }
        }

        Object object = invocation.proceed();

        if (!beforeInvocation) {
            if (frequentLogPrint) {
                log.info("After invocation, CacheEvict clear key={} in Redis", key);
            }

            try {
                clear(key, name, allEntries);
            } catch (Exception e) {
                log.warn("Redis exception occurs while setting data", e);
            }
        }

        return object;
    }

    private void clear(String key, String name, boolean allEntries) {
        String compositeWildcardKey = null;
        if (allEntries) {
            compositeWildcardKey = KeyUtil.getCompositeWildcardKey(prefix, name);
        } else {
            compositeWildcardKey = KeyUtil.getCompositeWildcardKey(key);
        }

        RedisTemplate<String, Object> redisTemplate = redisHandler.getRedisTemplate();
        Set<String> keys = redisTemplate.keys(compositeWildcardKey);
        for (String k : keys) {
            redisTemplate.delete(k);
        }
    }
}