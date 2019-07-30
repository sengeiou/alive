package com.alive.ruo.microservice.commons.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.base.properties
 * @title: SmsProperties
 * @description: TODO
 * @date 2019/4/12 11:19
 */
@Data
@ConfigurationProperties(prefix = "alive.ruo.sms.cn")
public class SmsProperties {

    private String url;
}
