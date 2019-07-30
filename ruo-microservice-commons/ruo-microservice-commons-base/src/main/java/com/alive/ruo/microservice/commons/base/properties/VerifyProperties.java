package com.alive.ruo.microservice.commons.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.base.properties
 * @title: VerifyProperties
 * @description: TODO
 * @date 2019/4/23 13:59
 */
@Data
@ConfigurationProperties(prefix = "alive.ruo.verify")
public class VerifyProperties {

    private String verifyUrl;

    private String captchaId;

    private String secretId;

    private String secretKey;
}
