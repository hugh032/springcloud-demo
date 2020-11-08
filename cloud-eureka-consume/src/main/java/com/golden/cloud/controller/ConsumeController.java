package com.golden.cloud.controller;

import com.golden.cloud.bean.User;
import com.golden.cloud.service.Provider8001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumeController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Provider8001Service provider8001Service;
    public static final String URL = "http://CLOUD-EUREKA-PROVIDER";

    @GetMapping("/consume")
    public String test() {
        String forObject = restTemplate.getForObject(URL + "/hello", String.class);
        return forObject;
    }

    @GetMapping("/feign/consume")
    public String feignTest() {
        return provider8001Service.hello();
    }
    @GetMapping("/feign/consumeTimeout")
    public String consumeTimeout() {
        return provider8001Service.timeoutHello();
    }
    @GetMapping("/feign/consumeMap")
    public String consumeMap() {
        System.out.println("*****************");
        User user = new User();
        user.setName("Hugh");
        user.setAge(32);
        return provider8001Service.helloWithQueryMap(user);
    }
}
