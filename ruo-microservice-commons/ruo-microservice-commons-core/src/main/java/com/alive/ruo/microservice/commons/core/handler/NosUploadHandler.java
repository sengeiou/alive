package com.alive.ruo.microservice.commons.core.handler;

import com.google.common.io.Files;
import com.netease.cloud.auth.BasicCredentials;
import com.netease.cloud.auth.Credentials;
import com.netease.cloud.services.nos.NosClient;
import com.netease.cloud.services.nos.model.*;
import com.alive.ruo.microservice.commons.base.dto.File;
import com.alive.ruo.microservice.commons.base.dto.Floder;
import com.alive.ruo.microservice.commons.base.enums.ErrorCodeEnum;
import com.alive.ruo.microservice.commons.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.core.handler
 * @title: NosUploadHandler
 * @description: 上传网易云处理器
 * @date 2019/4/12 11:39
 */
@Slf4j
@RefreshScope
public class NosUploadHandler extends AbstractUploadHandler{

    @Autowired
    private Environment env;

    public NosUploadHandler(){
        super("zh-cn");
    }

    private NosClient nosClient(String gameAbbr){
        Credentials credentials = new BasicCredentials(
                env.getProperty(MessageFormat.format("alive.game.{0}.nos.accessKey", gameAbbr)),
                env.getProperty(MessageFormat.format("alive.game.{0}.nos.secretKey", gameAbbr))
        );

        NosClient nosClient = new NosClient(credentials);

        nosClient.setEndpoint(env.getProperty(MessageFormat.format("alive.game.{0}.nos.endPoint", gameAbbr)));

        return nosClient;
    }

    @Override
    public String upload(String gameAbbr, String objectName, InputStream is){
        NosClient nosClient = nosClient(gameAbbr);

        String bucket = env.getProperty(MessageFormat.format("alive.game.{0}.nos.bucket", gameAbbr));

        boolean exists = nosClient.doesBucketExist(bucket);

        if(!exists){
            //设置你要创建桶的名称
            CreateBucketRequest request = new CreateBucketRequest(bucket);
            //设置桶的权限，如果不设置，默认为Private
            request.setCannedAcl(CannedAccessControlList.PublicRead);
            nosClient.createBucket(request);
        }

        ObjectMetadata objectMetadata = new ObjectMetadata();
        PutObjectResult result = nosClient.putObject(bucket, objectName, is, objectMetadata);

        log.info("成功上传bucket:[{}]中的nos文件[{}]", bucket , objectName);

        return MessageFormat.format("{0}/{1}", env.getProperty(MessageFormat.format("alive.game.{0}.nos.cdnUrl", gameAbbr)), result.getObjectName());
    }

    @Override
    public Floder expand(String gameAbbr) {
        NosClient nosClient = nosClient(gameAbbr);

        String bucket = env.getProperty(MessageFormat.format("alive.game.{0}.nos.bucket", gameAbbr));

        Floder root = Floder.builder().floderName("根目录").build();

        return buildFloder(nosClient,root, bucket, "根目录");
    }

    @Override
    public List<File> listFile(String gameAbbr, String prefix) {
        NosClient nosClient = nosClient(gameAbbr);

        String bucket = env.getProperty(MessageFormat.format("alive.game.{0}.nos.bucket", gameAbbr));

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucket);
        listObjectsRequest.setDelimiter("/");
        if(! "根目录".equals(prefix)){
            listObjectsRequest.setPrefix(prefix);
        }
        ObjectListing listing = nosClient.listObjects(listObjectsRequest);

        List<File> fileList = listing.getObjectSummaries().stream()
                .map(file -> File.builder()
                        .objectName(file.getKey())
                        .fileName(Files.getNameWithoutExtension(file.getKey()) + "." + Files.getFileExtension(file.getKey()))
                        .type(Files.getFileExtension(file.getKey()))
                        .fileUrl(MessageFormat.format("{0}/{1}", env.getProperty(MessageFormat.format("alive.game.{0}.nos.cdnUrl", gameAbbr)), file.getKey()))
                        .size(file.getSize()).build()
                ).collect(Collectors.toList());

        return fileList;
    }

    private Floder buildFloder(NosClient nosClient, Floder floder, String bucket, String prefix){
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucket);
        listObjectsRequest.setDelimiter("/");
        if(! "根目录".equals(prefix)){
            listObjectsRequest.setPrefix(prefix);
        }
        ObjectListing listing = nosClient.listObjects(listObjectsRequest);

        List<Floder> floderList = listing.getCommonPrefixes().stream()
                .filter(floderName -> "根目录".equals(prefix) || ! prefix.equals(floderName))
                .map(floderName -> {
                    Floder flodera =  Floder.builder().floderName(floderName).build();
                    //递归目录
                    buildFloder(nosClient, flodera, bucket, floderName);
                    return flodera;
                 }).collect(Collectors.toList());

        floder.setChildren(floderList);

        floder.setHasChild(floderList.isEmpty() ? false : true);

        floder.setHasFile(listing.getObjectSummaries().isEmpty() ? false : true);

        return floder;
    }

    @Override
    public void delete(String gameAbbr, String objectName) {
        NosClient nosClient = nosClient(gameAbbr);

        String bucket = env.getProperty(MessageFormat.format("alive.game.{0}.nos.bucket", gameAbbr));

        boolean exist = nosClient.doesObjectExist(bucket,objectName, null);

        if(! exist){
            throw new BusinessException(ErrorCodeEnum.COMMON90002);
        }

        nosClient.deleteObject(bucket, objectName);

        log.info("成功删除bucket:[{}]中的nos文件[{}]", bucket , objectName);
    }

}
