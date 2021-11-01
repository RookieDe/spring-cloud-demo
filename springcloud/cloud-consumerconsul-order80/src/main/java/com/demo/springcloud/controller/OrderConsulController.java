package com.demo.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName OrderConsulController.java
 * @createTime 2021-10-29 15:22:00
 */
@RestController
public class OrderConsulController {

    public static final String INVOKE_RUL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/payment/consul")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_RUL+"/payment/consul",String.class);
        return result;
    }
}