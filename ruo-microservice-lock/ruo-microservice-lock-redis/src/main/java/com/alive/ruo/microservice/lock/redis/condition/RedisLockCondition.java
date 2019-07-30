package com.alive.ruo.microservice.lock.redis.condition;

import com.alive.ruo.microservice.lock.condition.LockCondition;
import com.alive.ruo.microservice.lock.constant.LockConstant;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.redis.condition
 * @title: RedisLockCondition
 * @description: TODO
 * @date 2019/6/14 15:39
 */
public class RedisLockCondition extends LockCondition {

    public RedisLockCondition() {
        super(LockConstant.LOCK_TYPE, LockConstant.LOCK_TYPE_REDIS);
    }

}