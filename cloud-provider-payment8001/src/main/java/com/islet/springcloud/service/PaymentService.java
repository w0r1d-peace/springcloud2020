package com.islet.springcloud.service;

import com.islet.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author: islet
 * @date: 2020/6/30 19:30
 * @description:
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
