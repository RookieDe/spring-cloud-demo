package com.demo.rule;

import com.demo.springcloud.lb.CustomizeRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hongde
 * @version 1.0.0
 * @Description 自定义Ribbon轮询算法
 * @ClassName MySelfRule.java
 * @createTime 2021-11-18 20:23:00
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule sjRule(){
        return new CustomizeRule();
    }

}
