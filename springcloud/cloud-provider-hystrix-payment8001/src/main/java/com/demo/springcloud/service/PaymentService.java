package com.demo.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

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



    //------------------------服务熔断 start------------------------------
    /**
     * 服务熔断
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败了达到多少后进行熔断

    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("入参id不能为负数！！！");
        }
        String uuid = UUID.randomUUID().toString();

        return Thread.currentThread().getName() + "调用成功！！！！ 流水号：" + uuid;

    }

    public String paymentCircuitBreaker_fallback() {
        return "id不能为负数哦，请稍后再试！！！ToT~~";
    }

}
