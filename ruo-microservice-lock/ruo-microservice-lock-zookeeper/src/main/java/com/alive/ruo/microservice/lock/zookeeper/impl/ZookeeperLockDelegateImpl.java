package com.alive.ruo.microservice.lock.zookeeper.impl;

import com.alive.ruo.microservice.lock.LockDelegate;
import com.alive.ruo.microservice.lock.LockExecutor;
import com.alive.ruo.microservice.lock.entity.LockType;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.zookeeper.impl
 * @title: ZookeeperLockDelegateImpl
 * @description: TODO
 * @date 2019/7/30 14:46
 */
public class ZookeeperLockDelegateImpl implements LockDelegate {

    @Autowired
    private LockExecutor<InterProcessMutex> lockExecutor;

    @Override
    public Object invoke(MethodInvocation invocation, LockType lockType, String key, long leaseTime, long waitTime, boolean async, boolean fair) throws Throwable {
        InterProcessMutex interProcessMutex = null;
        try {
            boolean lock  = lockExecutor.tryLock(lockType, key, leaseTime, waitTime, async, fair);
            if (interProcessMutex != null) {
                return invocation.proceed();
            }
        } finally {
            lockExecutor.unlock();
        }

        return null;
    }
}