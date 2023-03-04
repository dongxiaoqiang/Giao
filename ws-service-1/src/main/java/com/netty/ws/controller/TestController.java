package com.netty.ws.controller;

import com.demo.feign.api.TestFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API接口 网关同样支持转发
 *
 * @author DongXiaoQiang
 * @Date 2020/09/18
 */
@Slf4j
@RestController
public class TestController {
    @Autowired
    private TestFeign testFeign;

    @GetMapping("/v1/api")
    public String testReq() {
        return "Hello Giao! This is ws-service-1!";
    }

    @GetMapping("/v1/feign")
    public String testFeign() {
        log.info("TestController This is ws-service-1");
        return testFeign.testFeign();
    }
}
