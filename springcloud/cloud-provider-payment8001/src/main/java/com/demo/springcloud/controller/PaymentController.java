package com.demo.springcloud.controller;

import com.demo.springcloud.entites.CommonResult;
import com.demo.springcloud.entites.Payment;
import com.demo.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*******element:"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SEVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }
}
