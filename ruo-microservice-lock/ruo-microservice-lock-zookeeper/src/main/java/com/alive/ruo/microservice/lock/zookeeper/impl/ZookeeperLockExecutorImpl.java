package com.alive.ruo.microservice.lock.zookeeper.impl;

import com.alive.ruo.microservice.commons.base.constant.GlobalConstant;
import com.alive.ruo.microservice.commons.zookeeper.handler.CuratorHandler;
import com.alive.ruo.microservice.lock.LockExecutor;
import com.alive.ruo.microservice.lock.entity.LockType;
import com.alive.ruo.microservice.lock.util.KeyUtil;
import com.alive.ruo.microservice.lock.zookeeper.exception.ZookeeperLockException;
import javafx.util.Pair;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.zookeeper.impl
 * @title: ZookeeperLockExecutorImpl
 * @description: TODO
 * @date 2019/7/30 14:44
 */
@Slf4j
public class ZookeeperLockExecutorImpl implements LockExecutor<InterProcessMutex> {
    @Autowired
    private CuratorHandler curatorHandler;

    @Value("${" + GlobalConstant.PREFIX + "}")
    private String prefix;

    // 可重入锁可重复使用
    private volatile Map<String, InterProcessMutex> lockMap = new ConcurrentHashMap<String, InterProcessMutex>();

    private volatile Map<String, InterProcessReadWriteLock> readWriteLockMap = new ConcurrentHashMap<String, InterProcessReadWriteLock>();

    private static ThreadLocal<Pair<InterProcessMutex, String>> threadLocal = new ThreadLocal<>();

    private boolean lockCached = true;

    @PreDestroy
    public void destroy() {
        try {
            curatorHandler.close();
        } catch (Exception e) {
            throw new ZookeeperLockException("Close Curator failed", e);
        }
    }

    @Override
    public boolean tryLock(LockType lockType, String name, String key, long leaseTime, long waitTime, boolean async, boolean fair){
        if (StringUtils.isEmpty(name)) {
            throw new ZookeeperLockException("Name is null or empty");
        }

        if (StringUtils.isEmpty(key)) {
            throw new ZookeeperLockException("Key is null or empty");
        }

        String compositeKey = KeyUtil.getCompositeKey(prefix, name, key);

        return tryLock(lockType, compositeKey, leaseTime, waitTime, async, fair);
    }

    @Override
    @SneakyThrows(Exception.class)
    public boolean tryLock(LockType lockType, String compositeKey, long leaseTime, long waitTime, boolean async, boolean fair) {
        if (StringUtils.isEmpty(compositeKey)) {
            throw new ZookeeperLockException("Composite key is null or empty");
        }

        if (fair) {
            throw new ZookeeperLockException("Fair lock of Zookeeper isn't support for " + lockType);
        }

        if (async) {
            throw new ZookeeperLockException("Async lock of Zookeeper isn't support for " + lockType);
        }

        curatorHandler.validateStartedStatus();

        InterProcessMutex interProcessMutex = getLock(lockType, compositeKey);
        boolean acquired = interProcessMutex.acquire(waitTime, TimeUnit.MILLISECONDS);

        return acquired;
    }

    @Override
    public void lock(LockType lockType, String name, String key, boolean async, boolean fair) {
        if (StringUtils.isEmpty(name)) {
            throw new ZookeeperLockException("Name is null or empty");
        }

        if (StringUtils.isEmpty(key)) {
            throw new ZookeeperLockException("Key is null or empty");
        }

        String compositeKey = KeyUtil.getCompositeKey(prefix, name, key);

        lock(lockType, compositeKey, async, fair);
    }

    @Override
    @SneakyThrows(Exception.class)
    public void lock(LockType lockType, String compositeKey, boolean async, boolean fair) {
        if (StringUtils.isEmpty(compositeKey)) {
            throw new ZookeeperLockException("Composite key is null or empty");
        }

        if (fair) {
            throw new ZookeeperLockException("Fair lock of Zookeeper isn't support for " + lockType);
        }

        if (async) {
            throw new ZookeeperLockException("Async lock of Zookeeper isn't support for " + lockType);
        }

        curatorHandler.validateStartedStatus();

        InterProcessMutex interProcessMutex = getLock(lockType, compositeKey);

        interProcessMutex.acquire();
    }

    @Override
    public void unlock() {
        String lockKey = null;
        try{
            if (!curatorHandler.isStarted()){
                return;
            }
            // 当前线程中获取到pair   如果没有获取到锁 没有必要做释放
            Pair<InterProcessMutex, String> pair = threadLocal.get();
            if (pair == null) {
                return;
            }
            InterProcessMutex lock = pair.getKey();
            lockKey = pair.getValue();
            if (lock != null && lock.isAcquiredInThisProcess()) {
                lock.release();
            }
        } catch (Exception e) {
            log.error("释放redis分布式锁异常,lockKey:{},e:", lockKey, e);
        } finally{
            threadLocal.remove();
        }
    }

    private InterProcessMutex getLock(LockType lockType, String key) {
        InterProcessMutex lock;
        if (lockCached) {
            lock = getCachedLock(lockType, key);
        } else {
            lock = getNewLock(lockType, key);
        }

        threadLocal.set(new Pair<>(lock, key));

        return lock;
    }

    private InterProcessMutex getNewLock(LockType lockType, String key) {
        String path = curatorHandler.getPath(prefix, key);
        CuratorFramework curator = curatorHandler.getCurator();
        switch (lockType) {
            case LOCK:
                return new InterProcessMutex(curator, path);
            case READ_LOCK:
                return getCachedReadWriteLock(lockType, key).readLock();
            // return new InterProcessReadWriteLock(curator, path).readLock();
            case WRITE_LOCK:
                return getCachedReadWriteLock(lockType, key).writeLock();
            // return new InterProcessReadWriteLock(curator, path).writeLock();
        }

        throw new ZookeeperLockException("Invalid Zookeeper lock type for " + lockType);
    }

    private InterProcessMutex getCachedLock(LockType lockType, String key) {
        String path = curatorHandler.getPath(prefix, key);
        String newKey = path + "-" + lockType;

        InterProcessMutex lock = lockMap.get(newKey);
        if (lock == null) {
            InterProcessMutex newLock = getNewLock(lockType, key);
            lock = lockMap.putIfAbsent(newKey, newLock);
            if (lock == null) {
                lock = newLock;
            }
        }

        return lock;
    }

    private InterProcessReadWriteLock getCachedReadWriteLock(LockType lockType, String key) {
        String path = curatorHandler.getPath(prefix, key);
        String newKey = path;

        InterProcessReadWriteLock readWriteLock = readWriteLockMap.get(newKey);
        if (readWriteLock == null) {
            CuratorFramework curator = curatorHandler.getCurator();
            InterProcessReadWriteLock newReadWriteLock = new InterProcessReadWriteLock(curator, path);
            readWriteLock = readWriteLockMap.putIfAbsent(newKey, newReadWriteLock);
            if (readWriteLock == null) {
                readWriteLock = newReadWriteLock;
            }
        }

        return readWriteLock;
    }
}