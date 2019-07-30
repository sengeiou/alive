package com.alive.ruo.microservice.lock.zookeeper.condition;

import com.alive.ruo.microservice.lock.condition.LockCondition;
import com.alive.ruo.microservice.lock.constant.LockConstant;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.lock.zookeeper.condition
 * @title: ZookeeperLockCondition
 * @description: TODO
 * @date 2019/7/30 14:40
 */
public class ZookeeperLockCondition extends LockCondition {
    public ZookeeperLockCondition() {
        super(LockConstant.LOCK_TYPE, LockConstant.LOCK_TYPE_ZOOKEEPER);
    }
}