package com.demo.springcloud.controller;

import com.demo.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName PaymentController.java
 * @createTime 2021-12-02 11:01:00
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "payment/hystrix/{id}")
    public String paymentInfo(@PathVariable("id") Integer id){
        String s = paymentService.paymentInfo(id);
        log.info("********成功的结果："+s);
        return s;
    }

    @GetMapping(value = "payment/hystrix/false/{id}")
    public String paymentInfoFalse(@PathVariable("id") Integer id){
        long l = System.currentTimeMillis();
        String s = paymentService.paymentInfoFalse(id);
        log.info("********不成功的结果："+s);
        long l1 = System.currentTimeMillis();
        return s+"耗时："+(l1-l);
    }

}
