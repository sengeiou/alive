package com.alive.ruo.microservice.cache.aop;

import com.alive.ruo.microservice.autoconfigure.proxy.aop.AbstractInterceptor;
import com.alive.ruo.microservice.cache.CacheDelegate;
import com.alive.ruo.microservice.cache.annotation.CacheEvict;
import com.alive.ruo.microservice.cache.annotation.CachePut;
import com.alive.ruo.microservice.cache.annotation.Cacheable;
import com.alive.ruo.microservice.cache.constant.CacheConstant;
import com.alive.ruo.microservice.cache.exception.CacheException;
import com.alive.ruo.microservice.cache.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Method;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.cache.aop
 * @title: CacheInterceptor
 * @description: TODO
 * @date 2019/7/5 15:36
 */
@Slf4j
public class CacheInterceptor extends AbstractInterceptor {

    @Autowired
    private CacheDelegate cacheDelegate;

    @Value("${" + CacheConstant.PREFIX + "}")
    private String prefix;

    @Value("${" + CacheConstant.FREQUENT_LOG_PRINT + ":false}")
    private Boolean frequentLogPrint;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Cacheable cacheableAnnotation = getCacheableAnnotation(invocation);
        if (cacheableAnnotation != null) {
            String name = cacheableAnnotation.name();
            String key = cacheableAnnotation.key();
            long expire = cacheableAnnotation.expire();

            return invokeCacheable(invocation, name, key, expire);
        }

        CachePut cachePutAnnotation = getCachePutAnnotation(invocation);
        if (cachePutAnnotation != null) {
            String name = cachePutAnnotation.name();
            String key = cachePutAnnotation.key();
            long expire = cachePutAnnotation.expire();

            return invokeCachePut(invocation, name, key, expire);
        }

        CacheEvict cacheEvictAnnotation = getCacheEvictAnnotation(invocation);
        if (cacheEvictAnnotation != null) {
            String name = cacheEvictAnnotation.name();
            String key = cacheEvictAnnotation.key();
            boolean allEntries = cacheEvictAnnotation.allEntries();
            boolean beforeInvocation = cacheEvictAnnotation.beforeInvocation();

            return invokeCacheEvict(invocation, name, key, allEntries, beforeInvocation);
        }

        return invocation.proceed();
    }

    private Cacheable getCacheableAnnotation(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        if (method.isAnnotationPresent(Cacheable.class)) {
            return method.getAnnotation(Cacheable.class);
        }

        return null;
    }

    private CachePut getCachePutAnnotation(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        if (method.isAnnotationPresent(CachePut.class)) {
            return method.getAnnotation(CachePut.class);
        }

        return null;
    }

    private CacheEvict getCacheEvictAnnotation(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        if (method.isAnnotationPresent(CacheEvict.class)) {
            return method.getAnnotation(CacheEvict.class);
        }

        return null;
    }

    private Object invokeCacheable(MethodInvocation invocation, String name, String key, long expire) throws Throwable {
        if (StringUtils.isEmpty(name)) {
            throw new CacheException("Annotation [Cacheable]'s name is null or empty");
        }

        if (StringUtils.isEmpty(key)) {
            throw new CacheException("Annotation [Cacheable]'s key is null or empty");
        }

        String spelKey = getSpelKey(invocation, key);
        String compositeKey = KeyUtil.getCompositeKey(prefix, name, spelKey);
        String proxyType = getProxyType(invocation);
        String proxiedClassName = getProxiedClassName(invocation);
        String methodName = getMethodName(invocation);

        if (frequentLogPrint){
            log.info("Intercepted for annotation - Cacheable [key={}, expire={}, proxyType={}, proxiedClass={}, method={}]", compositeKey, expire, proxyType, proxiedClassName, methodName);
        }

        return cacheDelegate.invokeCacheable(invocation, compositeKey, expire);
    }

    private Object invokeCachePut(MethodInvocation invocation, String name, String key, long expire) throws Throwable {
        if (StringUtils.isEmpty(name)) {
            throw new CacheException("Annotation [CachePut]'s name is null or empty");
        }

        if (StringUtils.isEmpty(key)) {
            throw new CacheException("Annotation [CachePut]'s key is null or empty");
        }

        String spelKey = getSpelKey(invocation, key);
        String compositeKey = KeyUtil.getCompositeKey(prefix, name, spelKey);
        String proxyType = getProxyType(invocation);
        String proxiedClassName = getProxiedClassName(invocation);
        String methodName = getMethodName(invocation);

        if (frequentLogPrint){
            log.info("Intercepted for annotation - CachePut [key={}, expire={}, proxyType={}, proxiedClass={}, method={}]", compositeKey, expire, proxyType, proxiedClassName, methodName);
        }

        return cacheDelegate.invokeCachePut(invocation, compositeKey, expire);
    }

    private Object invokeCacheEvict(MethodInvocation invocation, String name, String key, boolean allEntries, boolean beforeInvocation) throws Throwable {
        if (StringUtils.isEmpty(name)) {
            throw new CacheException("Annotation [CacheEvict]'s name is null or empty");
        }

        if (StringUtils.isEmpty(key)) {
            throw new CacheException("Annotation [CacheEvict]'s key is null or empty");
        }

        String spelKey = getSpelKey(invocation, key);
        String compositeKey = KeyUtil.getCompositeKey(prefix, name, spelKey);
        String proxyType = getProxyType(invocation);
        String proxiedClassName = getProxiedClassName(invocation);
        String methodName = getMethodName(invocation);

        if (frequentLogPrint){
            log.info("Intercepted for annotation - CacheEvict [key={}, allEntries={}, beforeInvocation={}, proxyType={}, proxiedClass={}, method={}]", compositeKey, allEntries, beforeInvocation, proxyType, proxiedClassName, methodName);
        }

        return cacheDelegate.invokeCacheEvict(invocation, compositeKey, name, allEntries, beforeInvocation);
    }
}