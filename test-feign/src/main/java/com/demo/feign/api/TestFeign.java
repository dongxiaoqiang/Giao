package com.demo.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "TEST-FEIGN")
public interface TestFeign {

    @GetMapping("testFeign")
    String testFeign();
}
