package com.demo.feign.controller;

import com.demo.feign.service.TestFeignImpl;
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
public class TestFeignController {
    @Autowired
    private TestFeignImpl testFeignImpl;

    /**
     * feign实现需要提供http api入口
     *
     * @return
     */
    @GetMapping("/testFeign")
    public String testFeign() {
        return testFeignImpl.testFeign();
    }

}
