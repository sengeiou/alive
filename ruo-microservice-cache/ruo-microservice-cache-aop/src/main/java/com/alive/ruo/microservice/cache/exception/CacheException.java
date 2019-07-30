package com.alive.ruo.microservice.cache.exception;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.exception
 * @title: LockException
 * @description: TODO
 * @date 2019/6/14 9:55
 */
public class CacheException extends RuntimeException {

    private static final long serialVersionUID = -5523106933655728813L;

    public CacheException() {
        super();
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }
}