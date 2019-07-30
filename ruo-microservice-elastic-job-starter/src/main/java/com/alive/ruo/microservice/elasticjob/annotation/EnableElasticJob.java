package com.alive.ruo.microservice.elasticjob.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.alive.ruo.microservice.elasticjob.autoconfigure.JobParserAutoConfiguration;
import org.springframework.context.annotation.Import;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.elasticjob.annotation
 * @title: EnableElasticJob
 * @description: ElasticJob 开启注解 <p>在Spring Boot 启动类上加此注解开启自动配置<p>
 * @date 2019/6/28 17:56
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({JobParserAutoConfiguration.class})
public @interface EnableElasticJob {

}