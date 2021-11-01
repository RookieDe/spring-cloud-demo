package com.demo.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName ApplicationContextConfig.java
 * @createTime 2021-10-25 11:30:00
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate restTemplate (){
        return new RestTemplate();
    }
}
