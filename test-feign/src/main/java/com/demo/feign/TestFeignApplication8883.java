package com.netty.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.demo")
@SpringBootApplication
@EnableDiscoveryClient
public class WebSocketApplication8881 {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication8881.class, args);
    }
}
