package com.demo.feign.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * feign具体业务实现
 */
@Slf4j
@Component
public class TestFeignImpl {
    public String testFeign() {
        log.info("feign rpc log!");
        return "This is api-feign!";
    }
}
