package com.demo.springcloud.controller;

import com.demo.springcloud.service.PaymentHystrixFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName OrderHystrixController.java
 * @createTime 2021-12-02 16:51:00
 */
@Slf4j
@RestController
@RequestMapping(value = "/consumer")
@DefaultProperties(defaultFallback = "payment_global_fallbackMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixFeignService paymentHystrixFeignService;

    //------------------------服务降级 start------------------------------

    @GetMapping(value = "/payment/hystrix/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {
        return paymentHystrixFeignService.paymentInfo(id);
    }

    @GetMapping(value = "/payment/hystrix/false/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfoFalseFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoFalse(@PathVariable("id") Integer id) {
        return paymentHystrixFeignService.paymentInfoFalse(id);
    }

    public String paymentInfoFalseFallback(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "熔断了！！！！";
    }

    //------------------------服务降级 end------------------------------


    /**
     * 全局fallback方法
     */
    public String payment_global_fallbackMethod() {
        return "Global异常处理信息，请稍后再试！！！ToT~~";
    }

}
