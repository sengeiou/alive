package com.alive.ruo.microservice.lock.local.impl;

import com.alive.ruo.microservice.lock.LockDelegate;
import com.alive.ruo.microservice.lock.LockExecutor;
import com.alive.ruo.microservice.lock.constant.LockConstant;
import com.alive.ruo.microservice.lock.entity.LockType;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.locks.Lock;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.local.impl
 * @title: LocalLockDelegateImpl
 * @description: TODO
 * @date 2019/6/14 18:04
 */
@Slf4j
public class LocalLockDelegateImpl implements LockDelegate {

    @Autowired
    private LockExecutor<Lock> lockExecutor;

    @Value("${" + LockConstant.LOCK_AOP_EXCEPTION_IGNORE + ":true}")
    private Boolean lockAopExceptionIgnore;

    @Override
    public Object invoke(MethodInvocation invocation, LockType lockType, String key, long leaseTime, long waitTime, boolean async, boolean fair) throws Throwable {
        try {
            boolean lock = lockExecutor.tryLock(lockType, key, leaseTime, waitTime, async, fair);
            if (lock) {
                return invocation.proceed();
            }
        } catch (Exception e) {
            if (lockAopExceptionIgnore) {
                log.error("Exception occurs while Lock", e);

                return invocation.proceed();
            } else {
                throw e;
            }
        } finally {
            lockExecutor.unlock();
        }

        return invocation.proceed();
    }
}