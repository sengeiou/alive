package com.alive.ruo.microservice.lock.aop;

import com.alive.ruo.microservice.autoconfigure.proxy.aop.DefaultAutoScanProxy;
import com.alive.ruo.microservice.autoconfigure.proxy.mode.ProxyMode;
import com.alive.ruo.microservice.autoconfigure.proxy.mode.ScanMode;
import com.alive.ruo.microservice.lock.annotation.Lock;
import com.alive.ruo.microservice.lock.annotation.ReadLock;
import com.alive.ruo.microservice.lock.annotation.WriteLock;

import java.lang.annotation.Annotation;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.aop
 * @title: LockAutoScanProxy
 * @description: TODO
 * @date 2019/6/14 14:58
 */
public class LockAutoScanProxy extends DefaultAutoScanProxy {
    private static final long serialVersionUID = -957037966342626931L;

    private String[] commonInterceptorNames;

    @SuppressWarnings("rawtypes")
    private Class[] methodAnnotations;

    public LockAutoScanProxy(String scanPackages) {
        super(scanPackages, ProxyMode.BY_METHOD_ANNOTATION_ONLY, ScanMode.FOR_METHOD_ANNOTATION_ONLY);
    }

    @Override
    protected String[] getCommonInterceptorNames() {
        if (commonInterceptorNames == null) {
            commonInterceptorNames = new String[] { "lockInterceptor" };
        }

        return commonInterceptorNames;
    }

    @Override
    protected Class<? extends Annotation>[] getMethodAnnotations() {
        if (methodAnnotations == null) {
            methodAnnotations = new Class[] { Lock.class, ReadLock.class, WriteLock.class };
        }

        return methodAnnotations;
    }
}