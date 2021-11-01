package com.demo.springcloud.service.impl;

import com.demo.springcloud.dao.PaymentDao;
import com.demo.springcloud.entites.Payment;
import com.demo.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName PaymentImpl.java
 * @createTime 2021-10-14 16:45:00
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
