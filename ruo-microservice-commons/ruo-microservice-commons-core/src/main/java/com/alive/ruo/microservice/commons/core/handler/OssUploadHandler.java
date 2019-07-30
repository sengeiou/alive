package com.alive.ruo.microservice.commons.core.handler;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.ObjectListing;
import com.google.common.io.Files;
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
 * @title: OssNoblesseUploadHandler
 * @description: TODO
 * @date 2019/5/5 14:49
 */
@Slf4j
@RefreshScope
public class OssUploadHandler extends AbstractUploadHandler{

    @Autowired
    private Environment env;

    public OssUploadHandler(){
        super("zh-hk", "zh-tw");
    }

    private OSSClient ossClient(String gameAbbr){
        CredentialsProvider credentials = new DefaultCredentialProvider(
                env.getProperty(MessageFormat.format("alive.game.{0}.oss.accessKeyId", gameAbbr)),
                env.getProperty(MessageFormat.format("alive.game.{0}.oss.secretAccessKey", gameAbbr))
        );
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(env.getProperty(MessageFormat.format("alive.game.{0}.oss.endPoint", gameAbbr)), credentials, (ClientConfiguration)null);

        return ossClient;
    }

    @Override
    public String upload(String gameAbbr, String objectName, InputStream is){
        OSSClient ossClient = ossClient(gameAbbr);

        String bucket = env.getProperty(MessageFormat.format("alive.game.{0}.oss.bucket", gameAbbr));

        boolean exists = ossClient.doesBucketExist(bucket);

        if(!exists){
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
            // 设置存储空间的权限为公共读，默认是私有。
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }

        // 上传文件流。
        ossClient.putObject(bucket, objectName, is);

        // 关闭OSSClient。
        ossClient.shutdown();

        return MessageFormat.format("{0}/{1}", env.getProperty(MessageFormat.format("alive.game.{0}.oss.cdnUrl", gameAbbr)), objectName);
    }

    @Override
    public Floder expand(String gameAbbr) {

        String bucket = env.getProperty(MessageFormat.format("alive.game.{0}.oss.bucket", gameAbbr));

        Floder root = Floder.builder().floderName("根目录").build();

        return buildFloder(gameAbbr,root, bucket, "根目录");
    }

    private Floder buildFloder(String gameAbbr, Floder floder, String bucket, String prefix){
        OSSClient ossClient = ossClient(gameAbbr);

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucket);
        listObjectsRequest.setDelimiter("/");
        if(! "根目录".equals(prefix)){
            listObjectsRequest.setPrefix(prefix);
        }
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);

        List<Floder> floderList = listing.getCommonPrefixes().stream()
                .filter(floderName -> "根目录".equals(prefix) || ! prefix.equals(floderName))
                .map(floderName -> {
                    Floder flodera =  Floder.builder().floderName(floderName).build();
                    //递归目录
                    buildFloder(gameAbbr, flodera, bucket, floderName);
                    return flodera;
                }).collect(Collectors.toList());

        floder.setChildren(floderList);

        floder.setHasChild(floderList.isEmpty() ? false : true);

        floder.setHasFile(listing.getObjectSummaries().isEmpty() ? false : true);

        // 关闭OSSClient。
        ossClient.shutdown();

        return floder;
    }


    @Override
    public List<File> listFile(String gameAbbr, String prefix) {
        OSSClient ossClient = ossClient(gameAbbr);

        String bucket = env.getProperty(MessageFormat.format("alive.game.{0}.oss.bucket", gameAbbr));

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucket);
        listObjectsRequest.setDelimiter("/");
        if(! "根目录".equals(prefix)){
            listObjectsRequest.setPrefix(prefix);
        }
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);

        // 关闭OSSClient。
        ossClient.shutdown();

        List<File> fileList = listing.getObjectSummaries().stream()
                .map(file -> File.builder()
                        .objectName(file.getKey())
                        .fileName(Files.getNameWithoutExtension(file.getKey()) + "." + Files.getFileExtension(file.getKey()))
                        .type(Files.getFileExtension(file.getKey()))
                        .fileUrl(MessageFormat.format("{0}/{1}", env.getProperty(MessageFormat.format("alive.game.{0}.oss.cdnUrl", gameAbbr)), file.getKey()))
                        .size(file.getSize()).build()
                ).collect(Collectors.toList());

        return fileList;
    }

    @Override
    public void delete(String gameAbbr, String objectName) {
        OSSClient ossClient = ossClient(gameAbbr);

        String bucket = env.getProperty(MessageFormat.format("alive.game.{0}.oss.bucket", gameAbbr));

        /*boolean exist = ossClient.doesObjectExist(bucket, objectName);

        if(! exist){
            throw new BusinessException(ErrorCodeEnum.COMMON90001);
        }*/

        ossClient.deleteObject(bucket, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();

        log.info("成功删除bucket:[{}]中的oss文件[{}]", bucket , objectName);
    }
}
