package com.golden.cloud.controller;

import com.golden.cloud.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {
    @Autowired
    private HystrixService hystrixService;
    @GetMapping("/hystrix/ok/do/{id}")
    public String ok(@PathVariable Integer id) {
       return hystrixService.ok(id);
    }
    @GetMapping("/hystrix/timeout/do/{id}")
    public String timeout(@PathVariable Integer id) {
        String timeout = hystrixService.timeout(id);
        System.out.println(timeout);
        return timeout;
    }
}
