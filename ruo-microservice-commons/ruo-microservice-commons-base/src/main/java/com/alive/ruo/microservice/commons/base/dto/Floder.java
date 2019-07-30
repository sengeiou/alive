package com.alive.ruo.microservice.commons.base.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.base.dto
 * @title: Floder
 * @description: TODO
 * @date 2019/6/20 11:18
 */
@Data
@Builder
public class Floder implements Serializable {

    private static final long serialVersionUID = 5204131092364091796L;

    /**
     * 文件夹名称
     */
    @Getter
    private String floderName;

    /**
     * 当前目录文件名称
     */
    /*private List<File> files;*/

    /**
     * 是否含有子节点
     */
    @Builder.Default
    private boolean hasChild = false;

    /**
     * 是否含有子节点
     */
    @Builder.Default
    private boolean hasFile = false;

    /**
     * 子节点集合
     */
    private List<Floder> children;

}