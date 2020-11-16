package com.golden.cloud.hystrix.service;

import org.springframework.stereotype.Component;

@Component
public class HystrixFallbackService implements HystrixService {
    @Override
    public String ok(Integer id) {
        return "HystrixFallbackService.ok()处理的降级";
    }

    @Override
    public String timeout(Integer id) {
        return "HystrixFallbackService.timeout()处理的降级";
    }

    @Override
    public String zipkin(Integer id) {
        return "HystrixFallbackService.zipkin()处理的降级";
    }
}
