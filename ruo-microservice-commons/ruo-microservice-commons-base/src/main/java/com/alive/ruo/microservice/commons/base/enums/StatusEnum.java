package com.alive.ruo.microservice.commons.base.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.base.enums
 * @title: StatusEnum
 * @description: TODO
 * @date 2019/4/3 9:50
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusEnum {

    VALID(0, "生效"),

    INVALID(1, "失效");

    @Getter
    private Integer status;

    @Getter
    private String desc;

}
