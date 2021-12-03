package com.demo.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName PaymentService.java
 * @createTime 2021-12-02 10:25:00
 */
@Service
public class PaymentService {

    public String paymentInfo(Integer id){
        return "线程池："+Thread.currentThread().getName() + "paymentInfo Ok,id:"+id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfoFalseFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfoFalse(Integer id){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName() + "paymentInfoFalse Ok,id:"+id;
    }

    public String paymentInfoFalseFallback(Integer id){
        return "线程池："+Thread.currentThread().getName() + "熔断了！！！！";
    }

}
