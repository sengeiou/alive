package com.alive.ruo.microservice.cache.aop;

import com.alive.ruo.microservice.autoconfigure.proxy.aop.DefaultAutoScanProxy;
import com.alive.ruo.microservice.autoconfigure.proxy.mode.ProxyMode;
import com.alive.ruo.microservice.autoconfigure.proxy.mode.ScanMode;
import com.alive.ruo.microservice.cache.annotation.CacheEvict;
import com.alive.ruo.microservice.cache.annotation.CachePut;
import com.alive.ruo.microservice.cache.annotation.Cacheable;

import java.lang.annotation.Annotation;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.cache.aop
 * @title: CacheAutoScanProxy
 * @description: TODO
 * @date 2019/7/5 15:35
 */
public class CacheAutoScanProxy extends DefaultAutoScanProxy {
    private static final long serialVersionUID = 5099476398968133135L;

    private String[] commonInterceptorNames;

    @SuppressWarnings("rawtypes")
    private Class[] methodAnnotations;

    public CacheAutoScanProxy(String scanPackages) {
        super(scanPackages, ProxyMode.BY_METHOD_ANNOTATION_ONLY, ScanMode.FOR_METHOD_ANNOTATION_ONLY);
    }

    @Override
    protected String[] getCommonInterceptorNames() {
        if (commonInterceptorNames == null) {
            commonInterceptorNames = new String[] { "cacheInterceptor" };
        }

        return commonInterceptorNames;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<? extends Annotation>[] getMethodAnnotations() {
        if (methodAnnotations == null) {
            methodAnnotations = new Class[] { Cacheable.class, CachePut.class, CacheEvict.class };
        }

        return methodAnnotations;
    }
}