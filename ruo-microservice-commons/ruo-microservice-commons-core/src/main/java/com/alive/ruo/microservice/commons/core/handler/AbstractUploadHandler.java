package com.alive.ruo.microservice.commons.core.handler;

import com.alive.ruo.microservice.commons.base.dto.File;
import com.alive.ruo.microservice.commons.base.dto.Floder;
import com.alive.ruo.microservice.commons.base.enums.ErrorCodeEnum;
import com.alive.ruo.microservice.commons.base.exception.BusinessException;
import com.alive.ruo.microservice.commons.base.properties.VerifyProperties;
import com.alive.ruo.microservice.commons.core.utils.CaptchaVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.core.handler
 * @title: AbstractSmsHandler
 * @description: TODO
 * @date 2019/4/23 11:22
 */
public abstract class AbstractUploadHandler {

    private final Set<String> supportRegions;

    @Autowired
    private Environment env;

    @Autowired
    private VerifyProperties properties;

    public AbstractUploadHandler(String...region){
        this.supportRegions = new HashSet<>(Arrays.asList(region));
    }

    /**
     * 当前处理器是否支持处理当前回调请求
     *
     * @param region 回调请求参数
     * @return 是否支持
     */
    public boolean isSupport(String region){
        return region != null && supportRegions.contains(region);
    }

    /**
     * 上传文件流
     * @param objectName
     * @param is
     * @return
     */
    abstract public String upload(String gameAbbr, String objectName, InputStream is);

    /**
     * 上传文件流
     * @param objectName
     * @param is
     * @return
     */
    public String upload(String gameAbbr,String validateCode, String objectName, InputStream is){
        if("test".equals(env.getProperty("spring.profiles.active")) || "prod".equals(env.getProperty("spring.profiles.active")) || "hk_prod".equals(env.getProperty("spring.profiles.active"))){
            CaptchaVerifier verifier = new CaptchaVerifier(properties.getVerifyUrl(), properties.getCaptchaId(), new CaptchaVerifier.SecretPair(properties.getSecretId(), properties.getSecretKey()));
            if(! verifier.verify(validateCode)){
                throw new BusinessException(ErrorCodeEnum.UAC10002);
            }
        }
        return this.upload(gameAbbr, objectName, is);
    }

    /**
     * 文件tree显示
     * @param gameAbbr
     * @return
     */
    abstract public Floder expand(String gameAbbr);

    /**
     *  文件展示
     * @param gameAbbr
     * @param prefix
     * @return
     */
    abstract public List<File> listFile(String gameAbbr, String prefix);

    /**
     *  删除文件
     * @param gameAbbr
     * @param objectName
     * @return
     */
    abstract public void delete(String gameAbbr, String objectName);

}
