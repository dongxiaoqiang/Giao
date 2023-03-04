package com.demo.feign.impl;

import org.springframework.stereotype.Component;

@Component
public class TestFeignImpl {
    public String testFeign() {
        return "This is api-feign!";
    }
}
