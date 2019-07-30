package com.alive.ruo.microservice.elasticjob.autoconfigure;

import com.alive.ruo.microservice.elasticjob.parser.JobConfParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.elasticjob.autoconfigure
 * @title: JobParserAutoConfiguration
 * @description: 任务自动配置
 * @date 2019/6/28 17:57
 */
@Configuration
@EnableConfigurationProperties(ZookeeperProperties.class)
public class JobParserAutoConfiguration {

    @Autowired
    private ZookeeperProperties zookeeperProperties;

    /**
     * 初始化Zookeeper注册中心
     * @return
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter() {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(zookeeperProperties.getServerLists(),
                zookeeperProperties.getNamespace());
        zkConfig.setBaseSleepTimeMilliseconds(zookeeperProperties.getBaseSleepTimeMilliseconds());
        zkConfig.setConnectionTimeoutMilliseconds(zookeeperProperties.getConnectionTimeoutMilliseconds());
        zkConfig.setDigest(zookeeperProperties.getDigest());
        zkConfig.setMaxRetries(zookeeperProperties.getMaxRetries());
        zkConfig.setMaxSleepTimeMilliseconds(zookeeperProperties.getMaxSleepTimeMilliseconds());
        zkConfig.setSessionTimeoutMilliseconds(zookeeperProperties.getSessionTimeoutMilliseconds());
        return new ZookeeperRegistryCenter(zkConfig);
    }

    @Bean
    public JobConfParser jobConfParser() {
        return new JobConfParser();
    }

}