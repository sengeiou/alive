package com.alive.ruo.microservice.commons.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.base.dto
 * @title: BaseTree
 * @description: The class Base tree.
 * @date 2019/4/16 17:29
 * @param <E>  the type parameter
 * @param <ID> the type parameter
 */
@Data
public class BaseTree<E, ID> implements Serializable {

    private static final long serialVersionUID = -5703964834600572016L;

    /**
     * ID
     */
    private ID id;

    /**
     * 父ID
     */
    private ID pid;

    /**
     * 是否含有子节点
     */
    private boolean hasChild = false;

    /**
     * 子节点集合
     */
    private List<E> children;
}
