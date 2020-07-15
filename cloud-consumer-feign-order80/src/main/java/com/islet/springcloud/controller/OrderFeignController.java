package com.islet.springcloud.controller;

import com.islet.springcloud.service.PaymentFeignService;
import com.islet.springcloud.entities.CommonResult;
import com.islet.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: islet
 * @date: 2020/7/9 17:50
 * @description:
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        //openfeign-ribbon，客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
