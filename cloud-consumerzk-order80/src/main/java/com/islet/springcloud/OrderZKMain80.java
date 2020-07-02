package com.islet.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: islet
 * @date: 2020/7/2 22:07
 * @description:
 */

@SpringBootApplication
@EnableDiscoveryClient  // 该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class OrderZKMain80 {

    public static void main(String[] args) {
            SpringApplication.run(OrderZKMain80.class, args);
        }
}
