package com.netty.ws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API接口 网关同样支持转发
 *
 * @author DongXiaoQiang
 * @Date 2020/09/18
 */
@RestController
public class TestController {

    @GetMapping("/v1/api")
    public String testReq() {
        return "Hello Giao! This is ws-service-1!";
    }
}
