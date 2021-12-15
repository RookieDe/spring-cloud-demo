package com.demo.springcloud.service.impl;

import com.demo.springcloud.service.PaymentHystrixFeignService;
import org.springframework.stereotype.Component;

/**
 * @author hongde
 * @version 1.0.0
 * @Description
 * @ClassName PaymentHystrixFeignServiceImpl.java
 * @createTime 2021-12-13 14:39:00
 */
@Component
public class PaymentHystrixFeignServiceImpl implements PaymentHystrixFeignService {
    @Override
    public String paymentInfo(Integer id) {
        return "对方服务器调用不通啊  --------paymentFallbackService fall back,0_0 !!!";
    }

    @Override
    public String paymentInfoFalse(Integer id) {
        return "对方服务器调用不通啊 --------paymentFallbackService fall back paymentInfoFalse,0_0 !!!";
    }
}
