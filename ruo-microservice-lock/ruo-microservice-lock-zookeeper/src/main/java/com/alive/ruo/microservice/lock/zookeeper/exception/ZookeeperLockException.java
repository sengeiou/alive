package com.alive.ruo.microservice.lock.zookeeper.exception;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.zookeeper.exception
 * @title: ProxyException
 * @description: TODO
 * @date 2019/6/14 9:55
 */
public class ZookeeperLockException extends RuntimeException {
    private static final long serialVersionUID = -5563106933655728813L;

    public ZookeeperLockException() {
        super();
    }

    public ZookeeperLockException(String message) {
        super(message);
    }

    public ZookeeperLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZookeeperLockException(Throwable cause) {
        super(cause);
    }
}