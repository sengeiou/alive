package org.apache.dubbo.config.spring.beans.factory.annotation;

import feign.Feign;
import feign.Target;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

/**
 * @author Somnus
 * @packageName com.alibaba.dubbo.config.spring.beans.factory.annotation
 * @title: DubboFeignBuilder
 * @description: TODO
 * @date 2019/3/18 11:14
 */
public class DubboFeignBuilder extends Feign.Builder {
    @Autowired
    private ApplicationContext applicationContext;

    public Reference defaultReference;
    final class DefaultReferenceClass{
        @Reference(check = false) String field;
    }

    public DubboFeignBuilder() {
        this.defaultReference = ReflectionUtils.findField(DubboFeignBuilder.DefaultReferenceClass.class,"field").getAnnotation(Reference.class);
    }


    @Override
    public <T> T target(Target<T> target) {
        ReferenceBeanBuilder beanBuilder = ReferenceBeanBuilder
                .create(defaultReference, target.getClass().getClassLoader(), applicationContext)
                .interfaceClass(target.type());

        try {
            T object = (T) beanBuilder.build().getObject();
            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}