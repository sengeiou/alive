package com.alive.ruo.microservice.lock.redis.configuration;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.alive.ruo.microservice.commons.redisson.adapter.RedissonAdapter;
import com.alive.ruo.microservice.commons.redisson.constant.RedissonConstant;
import com.alive.ruo.microservice.commons.redisson.handler.RedissonHandler;
import com.alive.ruo.microservice.commons.redisson.handler.RedissonHandlerImpl;
import com.alive.ruo.microservice.lock.LockDelegate;
import com.alive.ruo.microservice.lock.LockExecutor;
import com.alive.ruo.microservice.lock.redis.condition.RedisLockCondition;
import com.alive.ruo.microservice.lock.redis.impl.RedisLockDelegateImpl;
import com.alive.ruo.microservice.lock.redis.impl.RedisLockExecutorImpl;
import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.redis.configuration
 * @title: RedisLockConfiguration
 * @description: TODO
 * @date 2019/6/14 15:42
 */
@Configuration
public class RedisLockConfiguration {

    @Value("${" + RedissonConstant.PATH + ":" + RedissonConstant.DEFAULT_PATH + "}")
    private String redissonPath;

    @Value("${" + RedissonConstant.NACOS_URL + "}")
    private String nacosUrl;

    @Autowired(required = false)
    private RedissonAdapter redissonAdapter;

    @Bean
    @Conditional(RedisLockCondition.class)
    public LockDelegate redisLockDelegate() {
        return new RedisLockDelegateImpl();
    }

    @Bean
    @Conditional(RedisLockCondition.class)
    public LockExecutor<RLock> redisLockExecutor() {
        return new RedisLockExecutorImpl();
    }

    @Bean
    @SneakyThrows(Exception.class)
    @Conditional(RedisLockCondition.class)
    @ConditionalOnMissingBean
    public RedissonHandler redissonHandler() {
        if (redissonAdapter != null) {
            return redissonAdapter.getRedissonHandler();
        }
        /*return new RedissonHandlerImpl(redissonPath);*/
        Map<String,String> params = ImmutableMap.of("dataId", redissonPath, "group", "DEFAULT_GROUP");
        List<NameValuePair> pairs = params.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        URI uri = new URIBuilder("http://" + nacosUrl + "/nacos/v1/cs/configs").addParameters(pairs).build();
        return new RedissonHandlerImpl(uri);
    }
}