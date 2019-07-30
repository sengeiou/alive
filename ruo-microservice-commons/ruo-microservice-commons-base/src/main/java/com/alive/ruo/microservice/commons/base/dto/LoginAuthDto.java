package com.alive.ruo.microservice.commons.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.base.dto
 * @title: LoginAuthDto
 * @description: TODO
 * @date 2019/3/18 15:27
 */
@Data
@ApiModel(value = "登录人信息")
@NoArgsConstructor
@AllArgsConstructor
public class LoginAuthDto implements Serializable {

    private static final long serialVersionUID = -1137852221455042256L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "组织ID")
    private Long groupId;

    @ApiModelProperty(value = "组织名称")
    private String groupName;

    public LoginAuthDto(Long userId, String loginName, String userName) {
        this.userId = userId;
        this.loginName = loginName;
        this.userName = userName;
    }
}