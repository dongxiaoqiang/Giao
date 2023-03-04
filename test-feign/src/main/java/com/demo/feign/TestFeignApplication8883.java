package com.demo.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.demo.feign.api")
@SpringBootApplication
@EnableDiscoveryClient
public class TestFeignApplication8883 {
    public static void main(String[] args) {
        SpringApplication.run(TestFeignApplication8883.class, args);
    }
}
