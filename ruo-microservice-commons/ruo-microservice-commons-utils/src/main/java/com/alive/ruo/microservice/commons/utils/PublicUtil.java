package com.alive.ruo.microservice.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.utils
 * @title: PublicUtil
 * @description: The class Public util.
 * @date 2019/3/15 14:55
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PublicUtil {

    /**
     * 判断对象是否Empty(null或元素为0)
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     *
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        }
        if (pObj == "") {
            return true;
        }
        if (pObj instanceof String) {
            return ((String) pObj) == null || ((String) pObj).length() == 0;
        } else if (pObj instanceof Collection) {
            return ((Collection<?>) pObj) == null || ((Collection<?>) pObj).isEmpty();
        } else if (pObj instanceof Map) {
            return ((Map<?,?>) pObj) == null || ((Map<?,?>) pObj).size() == 0;
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素大于0)
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     *
     * @return boolean 返回的布尔值
     */
    public static boolean isNotEmpty(Object pObj) {
        return !isEmpty(pObj);
    }

    @SneakyThrows
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass){
        if (map == null){
            return null;
        }
        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }

        return obj;
    }

    @SneakyThrows
    public static Map<String, String> objectToMap(Object obj){
        if(obj == null){
            return null;
        }
        Map<String, String> map = new HashMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            //默认PropertyDescriptor会有一个class对象，剔除之
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, String.valueOf(value));
        }

        return map;
    }

}