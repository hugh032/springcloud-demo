package com.golden.cloud.controller;

import com.golden.cloud.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hystrix")
public class HystrixController {
    @Autowired
    private HystrixService hystrixService;
    @GetMapping("/ok/do/{id}")
    public String ok(@PathVariable Integer id) {
       return hystrixService.ok(id);
    }
    @GetMapping("/timeout/do/{id}")
    public String timeout(@PathVariable Integer id) {
        String timeout = hystrixService.timeout(id);
        System.out.println(timeout);
        return timeout;
    }
    @GetMapping("/hugh")
    public String hugh() {
        return "hugh...";
    }

    /**
     * zipkin
     * @param id
     * @return
     */
    @GetMapping("/zipkin/{id}")
    public String zipkin(@PathVariable("id") Integer id) {
        return hystrixService.zipkin(id);
    }
}
