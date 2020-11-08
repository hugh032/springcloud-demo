package com.golden.cloud.hystrix.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-EUREKA-PROVIDER-HYSTRIX" ,fallback = HystrixFallbackService.class)
public interface HystrixService {
    @GetMapping("/hystrix/ok/do/{id}")
    String ok(@PathVariable("id") Integer id);

    @GetMapping("/hystrix/timeout/do/{id}")
    String timeout(@PathVariable("id") Integer id);
}
