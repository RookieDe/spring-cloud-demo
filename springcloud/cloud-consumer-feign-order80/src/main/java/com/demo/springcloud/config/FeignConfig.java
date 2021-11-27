package com.demo.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName FeignConfig.java
 * @createTime 2021-11-27 17:48:00
 */
@Configuration
public class FeignConfig {

    /**
     * 开启feign日志
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
