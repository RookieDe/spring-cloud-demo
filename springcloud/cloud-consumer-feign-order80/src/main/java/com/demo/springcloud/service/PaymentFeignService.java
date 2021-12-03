package com.demo.springcloud.service;

import com.demo.springcloud.entites.CommonResult;
import com.demo.springcloud.entites.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName PaymentFeignService.java
 * @createTime 2021-11-27 16:02:00
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SEVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> get(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/get/timeout")
    String getTimeout();
}
