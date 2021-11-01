package com.demo.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName OrderZkController.java
 * @createTime 2021-10-25 11:04:00
 */
@RestController
public class OrderZkController {

    public static final String INVOKE_RUL = "http://cloud-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/payment/zk  ")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_RUL+"/payment/zk",String.class);
        return result;
    }
}
