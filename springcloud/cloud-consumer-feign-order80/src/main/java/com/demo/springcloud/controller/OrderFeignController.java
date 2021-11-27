package com.demo.springcloud.controller;

import com.demo.springcloud.entites.CommonResult;
import com.demo.springcloud.entites.Payment;
import com.demo.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName OrderFeignController.java
 * @createTime 2021-11-27 16:21:00
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        return paymentFeignService.get(id);
    }


    @GetMapping(value = "/consumer/payment/getTimeOut")
    public String getTimeOut() {
        return paymentFeignService.getTimeout();
    }


}
