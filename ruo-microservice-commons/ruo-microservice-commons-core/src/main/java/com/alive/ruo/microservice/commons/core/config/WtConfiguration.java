package com.alive.ruo.microservice.commons.core.config;

import com.alive.ruo.microservice.commons.base.properties.*;
import com.alive.ruo.microservice.commons.core.generator.IdGenerator;
import com.alive.ruo.microservice.commons.core.generator.SnowflakeIdGenerator;
import com.alive.ruo.microservice.commons.core.handler.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.core.config
 * @title: WtConfiguration
 * @description: TODO
 * @date 2019/4/12 11:01
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({
        VerifyProperties.class,
        SmsProperties.class,
        TwSmsProperties.class
})
public class WtConfiguration {

    private final SmsProperties cn;

    private final TwSmsProperties tw;

    @Bean
    public NosUploadHandler nosMcocUploadHandler() {
        return new NosUploadHandler();
    }

    @Bean
    public OssUploadHandler ossFishUploadHandler() {
        return new OssUploadHandler();
    }

    @Bean
    public IdGenerator idGenerator() {
        return new SnowflakeIdGenerator();
    }
}
