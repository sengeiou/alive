# ruo-microservice-elastic-job-starter

分布式定时任务组件starter

## 使用方法

1. 引入pom依赖

```xml
<dependency>
    <groupId>com.alive</groupId>
    <artifactId>ruo-microservice-elastic-job-starter</artifactId>
    <version>${project.parent.version}</version>
</dependency>
```

2. 添加配置

```yaml
elastic:
  job:
      zk:
        namespace: elaticjob
        serverLists: 172.31.197.122:2181,172.31.197.124:2181,172.31.197.124:2181
```

3. 开启注解

```java
package com.alive.wt.microservice;

import com.alive.wt.microservice.elasticjob.annotation.EnableElasticJob;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.provider.uac
 * @title: UacProviderApplication
 * @description: 用户服务中心启动类
 * @date 2019/3/18 13:43
 */
@EnableElasticJob
public class UacProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UacProviderApplication.class, args);
    }

}

```