package com.demo.springcloud.dao;

import com.demo.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName PaymentDao.java
 * @createTime 2021-10-14 16:27:00
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id")Long id);

}
