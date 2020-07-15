package com.islet.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: islet
 * @date: 2020/7/10 10:54
 * @description:
 */
@Service
public class PaymentService {

    public String paymentInfoOk(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK,id：" + id + "\t"+"O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoTimeout(Integer id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id：" + id + "\t"+"O(∩_∩)O哈哈~"+" 耗时3秒钟";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id：" + id + "\t"+"┭┮﹏┭┮~";
    }

    //====服务熔断
    // 涉及到断路器的三个重要参数：快照时间窗、请求总数阀值、错误百分比阀值。
    // 1.快照时间窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照的时间窗，默认为最近的10秒。
    // 2.请求总数阀值：在快照时间窗内，必须满足请求总数阀值才有资格熔断。默认为20，意味着在10秒内，如果该hystrix命令的调用次数不足20次，
    // 即使所有的请求都超过或其它原因失败，断路器都不会打开。
    // 3.错误百分比阀值：当请求总数在快照时间窗内超过了阀值，比如发生了30次调用，如果在这30次调用中，有15次发生了超时异常，也就是超过50%
    // 的错误百分比，在默认设定50%阀值情况下，这时候就会将断路器打开。
    @HystrixCommand(fallbackMethod = "paymentCircuiBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******Id 不能负数");
        }

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuiBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，┭┮﹏┭┮~~。~~ id：" + id;
    }
}
