package com.alive.ruo.microservice.commons.base.enums;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.base.enums
 * @title: PlatformEnum
 * @description: TODO
 * @date 2019/5/16 13:38
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PlatformEnum {

    WINDOWS("Windows"),

    MAC("Mac"),

    ANDROID("Android"),

    IPHONE("iPhone"),

    IPAD("iPad"),

    UNKNOWN("Unknown");

    private String platform;

    /**
     * Gets enum.
     *
     * @param userAgent the name
     *
     * @return the enum
     */
    public static PlatformEnum platformOf(String userAgent) {
        for (PlatformEnum platform : PlatformEnum.values()) {
            UserAgent agent = UserAgent.parseUserAgentString(userAgent);
            /*Browser browser = agent.getBrowser();*/
            OperatingSystem os = agent.getOperatingSystem();
            if (StringUtils.contains(os.getName(), platform.getPlatform())) {
                return platform;
            }
        }
        return null;
    }

    /**
     * Gets enum.
     *
     * @param platform the platform
     *
     * @return the enum
     */
    public static PlatformEnum platOf(String platform) {
        for (PlatformEnum plat : PlatformEnum.values()) {
            if (plat.getPlatform().equalsIgnoreCase(platform)) {
                return plat;
            }
        }
        return null;
    }

}
