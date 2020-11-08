package com.golden.cloud.service;

import com.golden.cloud.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(name = "CLOUD-EUREKA-PROVIDER")
public interface Provider8001Service {
    @GetMapping("/hello")
    String hello();
    @GetMapping("/timeout/hello")
    String timeoutHello();
    @GetMapping("/helloWithQueryMap")
    String helloWithQueryMap(@SpringQueryMap User user);
}
