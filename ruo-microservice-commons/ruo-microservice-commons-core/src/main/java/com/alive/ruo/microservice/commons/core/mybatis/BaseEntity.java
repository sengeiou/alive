package com.alive.ruo.microservice.commons.core.mybatis;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.alive.ruo.microservice.commons.base.dto.LoginAuthDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.core.mybatis
 * @title: BaseEntity
 * @description: The class Base entity.
 * @date 2019/3/15 16:30
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 2393269568666085258L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 最近操作人
     */
    @Column(name = "last_operator")
    private String lastOperator;

    /**
     * 最后操作人ID
     */
    @Column(name = "last_operator_id")
    private Long lastOperatorId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Transient
    private Integer pageNum;

    @Transient
    private Integer pageSize;

    @Transient
    private String orderBy;

    /**
     * Is new boolean.
     *
     * @return the boolean
     */
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    /**
     * Sets update info.
     *
     * @param user the user
     */
    @Transient
    @JsonIgnore
    public void setUpdateInfo(LoginAuthDto user) {

        if (isNew()) {
            this.creatorId = (this.lastOperatorId = user.getUserId());
            this.creator = user.getUserName();
            this.createdTime = (this.updateTime = new Date());
        }
        this.lastOperatorId = user.getUserId();
        this.lastOperator = user.getUserName() == null ? user.getLoginName() : user.getUserName();
        this.updateTime = new Date();
    }

    @Transient
    @JsonIgnore
    public void creator() {
        this.creatorId = 0L;
        this.creator = "admin";
        this.createdTime = (this.updateTime = new Date());
    }
}
