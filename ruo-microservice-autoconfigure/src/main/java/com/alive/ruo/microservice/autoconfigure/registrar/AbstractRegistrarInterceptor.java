package com.alive.ruo.microservice.autoconfigure.registrar;

import com.alive.ruo.microservice.autoconfigure.proxy.aop.AbstractInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.MutablePropertyValues;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.autoconfigure.registrar
 * @title: AbstractRegistrarInterceptor
 * @description: TODO
 * @date 2019/6/14 9:58
 */
public abstract class AbstractRegistrarInterceptor extends AbstractInterceptor {
    protected MutablePropertyValues annotationValues;

    public AbstractRegistrarInterceptor(MutablePropertyValues annotationValues) {
        this.annotationValues = annotationValues;
    }

    public MutablePropertyValues getAnnotationValues() {
        return annotationValues;
    }

    public String getInterface(MethodInvocation invocation) {
        return getMethod(invocation).getDeclaringClass().getCanonicalName();
    }
}