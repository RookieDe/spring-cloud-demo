package com.demo.springcloud.service;

import com.demo.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName PaymentService.java
 * @createTime 2021-10-14 16:32:00
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id")Long id);
}
