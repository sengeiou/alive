package com.alive.ruo.microservice.commons.core.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.core.mybatis
 * @title: MyMapper
 * @description: The interface My mapper.
 * @date 2019/3/15 16:31
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
