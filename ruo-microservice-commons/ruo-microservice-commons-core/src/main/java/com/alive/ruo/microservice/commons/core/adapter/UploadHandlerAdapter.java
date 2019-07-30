package com.alive.ruo.microservice.commons.core.adapter;

import com.alive.ruo.microservice.commons.core.handler.AbstractUploadHandler;
import lombok.Data;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.OrderComparator;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.core.adapter
 * @title: RedeemHandlerAdapter
 * @description: 回调请求处理器适配器
 * @date 2019/3/28 13:56
 */
@Data
@Service
public class UploadHandlerAdapter implements InitializingBean, ApplicationListener<ContextRefreshedEvent> {

    private boolean             detectAllHandlers = true;

    private List<AbstractUploadHandler> uploadHandlers;

    @Autowired
    private Environment env;

    /**
     * 获得一个能够处理当前回调请求的处理器
     * @param gameAbbr 回调请求参数
     * @return 能够处理当前请求的处理器(如果未找到,则抛异常)
     */
    public AbstractUploadHandler getHandler(String gameAbbr){
        int count = uploadHandlers.size();
        for (int i = 0; i < count; i++) {
            AbstractUploadHandler handler = uploadHandlers.get(i);
            String region = MessageFormat.format("alive.game.{0}.region", gameAbbr);
            if(handler.isSupport(env.getProperty(region))){
                return handler;
            }
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() {
        uploadHandlers = uploadHandlers == null ? new ArrayList<>() : uploadHandlers;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        if(context.getParent() != null && this.detectAllHandlers){
            Map<String, AbstractUploadHandler> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, AbstractUploadHandler.class, true, false);
            if (!matchingBeans.isEmpty()) {
                uploadHandlers.addAll(matchingBeans.values());
                OrderComparator.sort(this.uploadHandlers);
            }
        }
    }
}
