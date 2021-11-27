package com.demo.springcloud.controller;

import com.demo.springcloud.entites.CommonResult;
import com.demo.springcloud.entites.Payment;
import com.demo.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName PaymentController.java
 * @createTime 2021-10-14 16:47:00
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("***插入结果："+i);
        if (i>0){
            return new CommonResult<>(200,"插入数据库成功,serverPort:"+serverPort,i);
        }else {
            return new CommonResult<>(500,"数据库插入失败",null);
        }
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询结果："+payment);
        if (payment != null){
            return new CommonResult<>(200,"查询数据库成功,serverPort:"+serverPort,payment);
        }else {
            return new CommonResult<>(500,"没有对应记录，查询id："+id,null);
        }
    }

    @GetMapping(value = "/payment/get/lb")
    public String getPaymentLb(){
        return "这个式8002返回的信息:"+serverPort;
    }

    @GetMapping(value = "/payment/get/timeout")
    public String getTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "这个是超时8002返回的信息:"+serverPort;
    }
}
