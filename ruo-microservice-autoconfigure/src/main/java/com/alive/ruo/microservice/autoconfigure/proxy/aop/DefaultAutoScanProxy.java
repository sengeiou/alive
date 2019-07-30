package com.alive.ruo.microservice.autoconfigure.proxy.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.alive.ruo.microservice.autoconfigure.proxy.mode.ProxyMode;
import com.alive.ruo.microservice.autoconfigure.proxy.mode.ScanMode;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.autoconfigure.proxy.aop
 * @title: DefaultAutoScanProxy
 * @description: TODO
 * @date 2019/6/14 9:54
 */
public class DefaultAutoScanProxy extends AbstractAutoScanProxy {
    private static final long serialVersionUID = 9073263289068871774L;

    public DefaultAutoScanProxy() {
        super();
    }

    public DefaultAutoScanProxy(String scanPackages) {
        super(scanPackages);
    }

    public DefaultAutoScanProxy(String[] scanPackages) {
        super(scanPackages);
    }

    public DefaultAutoScanProxy(ProxyMode proxyMode, ScanMode scanMode) {
        super(proxyMode, scanMode);
    }

    public DefaultAutoScanProxy(String scanPackages, ProxyMode proxyMode, ScanMode scanMode) {
        super(scanPackages, proxyMode, scanMode);
    }

    public DefaultAutoScanProxy(String[] scanPackages, ProxyMode proxyMode, ScanMode scanMode) {
        super(scanPackages, proxyMode, scanMode);
    }

    public DefaultAutoScanProxy(ProxyMode proxyMode, ScanMode scanMode, boolean exposeProxy) {
        super(proxyMode, scanMode, exposeProxy);
    }

    public DefaultAutoScanProxy(String scanPackages, ProxyMode proxyMode, ScanMode scanMode, boolean exposeProxy) {
        super(scanPackages, proxyMode, scanMode, exposeProxy);
    }

    public DefaultAutoScanProxy(String[] scanPackages, ProxyMode proxyMode, ScanMode scanMode, boolean exposeProxy) {
        super(scanPackages, proxyMode, scanMode, exposeProxy);
    }

    @Override
    protected Class<? extends MethodInterceptor>[] getCommonInterceptors() {
        return null;
    }

    @Override
    protected String[] getCommonInterceptorNames() {
        return null;
    }

    @Override
    protected MethodInterceptor[] getAdditionalInterceptors(Class<?> targetClass) {
        return null;
    }

    @Override
    protected Class<? extends Annotation>[] getClassAnnotations() {
        return null;
    }

    @Override
    protected Class<? extends Annotation>[] getMethodAnnotations() {
        return null;
    }

    @Override
    protected void classAnnotationScanned(Class<?> targetClass, Class<? extends Annotation> classAnnotation) {

    }

    @Override
    protected void methodAnnotationScanned(Class<?> targetClass, Method method, Class<? extends Annotation> methodAnnotation) {

    }
}
