package com.alive.ruo.microservice.cache.util;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.cache.util
 * @title: KeyUtil
 * @description: TODO
 * @date 2019/6/14 15:09
 */
public class KeyUtil {

    public static String getCompositeKey(String prefix, String name, String key) {
        return prefix + "_" + name + "_" + key;
    }

    public static String getCompositeWildcardKey(String prefix, String name) {
        return prefix + "_" + name + "*";
    }

    public static String getCompositeWildcardKey(String key) {
        return key + "*";
    }
}