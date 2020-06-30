package com.islet.springcloud.service.impl;

import com.islet.springcloud.dao.PaymentDao;
import com.islet.springcloud.entities.Payment;
import com.islet.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: islet
 * @date: 2020/6/30 19:33
 * @description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
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
