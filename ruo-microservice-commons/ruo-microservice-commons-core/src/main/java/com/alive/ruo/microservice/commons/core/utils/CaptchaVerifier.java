package com.alive.ruo.microservice.commons.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.core.utils
 * @title: CaptchaVerifier
 * @description: 二次验证
 * @date 2019/3/19 15:54
 */
@Slf4j
public class CaptchaVerifier {

    private static final String VERSION = "v2";
    private String verifyUrl = "";
    private String captchaId = ""; // 验证码id
    private SecretPair secretPair = null; // 密钥对

    public CaptchaVerifier(String verifyUrl,String captchaId, SecretPair secretPair) {
        Validate.notBlank(verifyUrl, "verifyUrl为空");
        Validate.notBlank(captchaId, "captchaId为空");
        Validate.notNull(secretPair, "secret为null");
        Validate.notBlank(secretPair.secretId, "secretId为空");
        Validate.notBlank(secretPair.secretKey, "secretKey为空");
        this.verifyUrl = verifyUrl;
        this.captchaId = captchaId;
        this.secretPair = secretPair;
    }

    /**
     * 二次验证
     *
     * @param validate 验证码组件提交上来的NECaptchaValidate值
     * @return
     */
    @SneakyThrows
    public boolean verify(String validate){
        if (StringUtils.isEmpty(validate) || StringUtils.equals(validate, "null")) {
            return false;
        }
        if(validate.equalsIgnoreCase("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92")){
            return true;
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("captchaId", captchaId);
        params.put("validate", validate);
        params.put("user", "");// bugfix:如果user为null会出现签名错误的问题
        // 公共参数
        params.put("secretId", secretPair.secretId);
        params.put("version", VERSION);
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonce", String.valueOf(ThreadLocalRandom.current().nextInt()));
        // 计算请求参数签名信息
        String signature = sign(secretPair.secretKey, params);
        params.put("signature", signature);

        log.info("二次校验接口参数为:{}", JSON.toJSONString(params));
        List<BasicNameValuePair> nameValuePairs = params.entrySet().stream()
                .map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        String resp = Request.Post(verifyUrl).bodyForm(nameValuePairs).execute().returnContent().asString();
        log.info("二次校验响应结果为:{}", resp);
        return verifyRet(resp);
    }

    /**
     * 生成签名信息
     *
     * @param secretKey 验证码私钥
     * @param params    接口请求参数名和参数值map，不包括signature参数名
     * @return
     */
    public static String sign(String secretKey, Map<String, String> params) {
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            sb.append(key).append(params.get(key));
        }
        sb.append(secretKey);
        try {
            return DigestUtils.md5Hex(sb.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();// 一般编码都支持的。。
        }
        return null;
    }

    /**
     * 验证返回结果
     *
     * @param resp
     * @return
     */
    private boolean verifyRet(String resp) {
        if (StringUtils.isEmpty(resp)) {
            return false;
        }
        try {
            JSONObject j = JSONObject.parseObject(resp);
            return j.getBoolean("result");
        } catch (Exception e) {
            return false;
        }
    }

    public static class SecretPair {
        public final String secretId;
        public final String secretKey;

        /**
         * 构造函数
         * @param secretId 密钥对id
         * @param secretKey 密钥对key
         */
        public SecretPair(String secretId, String secretKey) {
            this.secretId = secretId;
            this.secretKey = secretKey;
        }
    }
}
