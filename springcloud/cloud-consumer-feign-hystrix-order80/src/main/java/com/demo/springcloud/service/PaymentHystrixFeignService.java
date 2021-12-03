package com.demo.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName PaymentHystrixFeignService.java
 * @createTime 2021-12-02 16:40:00
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface PaymentHystrixFeignService {

    @GetMapping(value ="/payment/hystrix/{id}")
    public String paymentInfo(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/false/{id}")
    public String paymentInfoFalse(@PathVariable("id") Integer id);

}
