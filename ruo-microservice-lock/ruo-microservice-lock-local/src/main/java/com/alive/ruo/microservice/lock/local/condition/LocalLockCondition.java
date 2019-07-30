package com.alive.ruo.microservice.lock.local.condition;

import com.alive.ruo.microservice.lock.condition.LockCondition;
import com.alive.ruo.microservice.lock.constant.LockConstant;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.local.condition
 * @title: LocalLockCondition
 * @description: TODO
 * @date 2019/6/14 18:00
 */
public class LocalLockCondition extends LockCondition {

    public LocalLockCondition() {
        super(LockConstant.LOCK_TYPE, LockConstant.LOCK_TYPE_LOCAL);
    }

}