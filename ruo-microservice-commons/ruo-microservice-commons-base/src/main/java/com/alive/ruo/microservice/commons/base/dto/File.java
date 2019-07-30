package com.alive.ruo.microservice.commons.base.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.base.dto
 * @title: File
 * @description: TODO
 * @date 2019/6/20 17:18
 */
@Data
@Builder
public class File implements Serializable {

    private static final long serialVersionUID = 5204131092338091796L;

    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件全称
     */
    private String objectName;
    /**
     * 文件路径
     */
    private String fileUrl;
    /**
     * 文件大小
     */
    private Long size;
}