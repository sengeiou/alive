package com.alive.ruo.microservice.commons.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.utils
 * @title: JwtTokenUtil
 * @description: TODO
 * @date 2019/5/20 10:12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtTokenUtil {

    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    static {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            @Cleanup
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("keystore/jwt.jks"); // 寻找证书文件
            keyStore.load(inputStream, "123456".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray());
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成token
     * @param subject 主体信息
     * @param expirationSeconds 过期时间（秒）
     * @param claims 自定义身份信息
     * @return
     */
    public static String generateToken(String subject, int expirationSeconds, Map<String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     *  解析token,获得subject中的信息
     * @param token
     * @return
     */
    public static String parseToken(String token) {
        String subject = null;
        try {
            Claims claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

}
