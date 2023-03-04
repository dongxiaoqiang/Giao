package com.demo.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 拆分client包对外提供即可
 */
@FeignClient(name = "test-feign")
public interface TestFeign {

    @GetMapping("/testFeign")
    String testFeign();
}
