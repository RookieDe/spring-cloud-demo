package com.demo.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName PaymentController.java
 * @createTime 2021-10-21 17:58:00
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul(){
        return "spring cloud with consul:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }

}
