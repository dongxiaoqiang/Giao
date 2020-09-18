package com.netty.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WebSocketApplication8801 {
  public static void main(String[] args) {
    SpringApplication.run(WebSocketApplication8801.class, args);
  }
}
