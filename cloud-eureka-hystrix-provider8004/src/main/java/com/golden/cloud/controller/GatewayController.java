package com.golden.cloud.controller;

import com.golden.cloud.common.ApiResult;
import com.golden.cloud.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/gateway/demo")
@RestController
@Slf4j
public class GatewayController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private GatewayService gatewayService;
    @GetMapping("/t1/{id}")
    public ApiResult t1(@PathVariable("id") Integer id) {
        log.warn("this server is:{}",port);
        return gatewayService.t1(id);
    }

}
