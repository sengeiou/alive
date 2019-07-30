package com.alive.ruo.microservice.commons.utils.https;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.KeyStore;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.utils.https
 * @title: KeyStoreMaterial
 * @description: TODO
 * @date 2019/3/15 16:56
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KeyStoreMaterial {

    /**  密码 */
    private String password;

    /** keyStore */
    private KeyStore keyStore;

}