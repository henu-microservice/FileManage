package com.henu.seafile.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Pangpd
 * @Description
 * @Date 2019/5/14 13:46
 */

@Configuration
@ConfigurationProperties(prefix = "seafile")
public class SeafileConfig {

    public static class seafile{
        private String baseUrl;
        private String user;
    }
}
