package com.alive.ruo.microservice.lock.redis.exception;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.autoconfigure.proxy.exception
 * @title: ProxyException
 * @description: TODO
 * @date 2019/6/14 9:55
 */
public class RedisLockException extends RuntimeException {
    private static final long serialVersionUID = -5563106933655728813L;

    public RedisLockException() {
        super();
    }

    public RedisLockException(String message) {
        super(message);
    }

    public RedisLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisLockException(Throwable cause) {
        super(cause);
    }
}