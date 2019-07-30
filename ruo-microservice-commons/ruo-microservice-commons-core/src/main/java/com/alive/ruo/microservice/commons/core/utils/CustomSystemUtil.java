package com.alive.ruo.microservice.commons.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;

import java.net.*;
import java.util.Enumeration;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.utils
 * @title: CustomSystemUtil
 * @description: 系统工具类，用于获取系统相关信息
 * @date 2019/4/18 13:49
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomSystemUtil {
    /**
     * 内网IP
     */
    public static final String INTRANET_IP = getIntranetIp();

    /**
     * 外网IP
     */
    public static final String INTERNET_IP = getInternetIp();

    /**
     * 系统的主机名称
     */
    public static final String HOST_NAME = getHostName();

    private static InetAddress init() throws UnknownHostException, SocketException {
        InetAddress  localhost;
        try {
            //windows系统
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            //linux系统
            localhost = getLocalHost();
        }
        /*ip = localhost.getHostAddress().toString();
        hostName = localhost.getHostName().toString();*/
        return localhost;
    }

    /**
     * linux 如若想用InetAddress.getLocalHost()方式获取本机ip，需要保证以下一点
     * '/etc/hosts'配置中 有一行配置
     *                 			127.0.0.1  主机名
     *  centOS主机名在'/etc/sysconfig/network' 	配置中可以找到
     *  Ubuntu主机名在'/etc/network/interfaces' 	配置中可以找到
     *  不然就只能采取现提供的方法
     * @return String
     */
    private static InetAddress getLocalHost() throws UnknownHostException, SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces != null && interfaces.hasMoreElements()) {
            Enumeration<InetAddress> addresses = interfaces.nextElement().getInetAddresses();
            while (addresses != null && addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (address != null && !address.isLoopbackAddress() && !address.isAnyLocalAddress() && !address.isLinkLocalAddress()) {
                    return address;
                }
            }
        }
        throw new UnknownHostException();
    }

    /**
     * 获得内网IP
     *
     * @return 内网IP
     */
    private static String getIntranetIp() {
        try {
            return init().getHostAddress();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     *
     * @return 外网IP
     */
    private static String getInternetIp() {
        String response;
        try {
            response = Request.Get("http://pv.sohu.com/cityjson?ie=utf-8").execute().returnContent().asString();
            String json = StringUtils.substringBetween(response, "=", ";").trim();
            JSONObject obj = JSON.parseObject(json);
            return obj.getString("cip");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得系统的主机名称
     *
     * @return 系统的主机名称
     */
    private static String getHostName(){
        try {
            return init().getHostName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}