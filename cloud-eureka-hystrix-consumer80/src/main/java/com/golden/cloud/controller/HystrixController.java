package com.golden.cloud.controller;

import com.golden.cloud.hystrix.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@DefaultProperties(defaultFallback = "globalFallback")
public class HystrixController {

    @Autowired
    private HystrixService hystrixService;

    @GetMapping("/consumer/hystrix/ok/do/{id}")
    @HystrixCommand
    String consumerOk(@PathVariable Integer id) {
        //int value = 1 / 0;
        String ok = hystrixService.ok(id);
        System.out.println(ok);
        return ok;
    }

    @GetMapping("/consumer/hystrix/timeout/do/{id}")
//    @HystrixCommand(fallbackMethod = "timeoutFallback", commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds" ,value = "1000")
//    })
    //@HystrixCommand
    String consumerTimeout(@PathVariable Integer id) {
        //int value = 1 / 0;
        String timeout = hystrixService.timeout(id);
        System.out.println(timeout);
        return timeout;
    }

    /**
     * fallback 方法需要和对应方法同参
     *
     * @param id
     * @return
     */
    public String timeoutFallback(Integer id) {
        return "consumer端 " + Thread.currentThread().getName() + " timeout fallback..";
    }

    public String globalFallback() {
        return "consumer端 全局fallback";
    }

    /**
     * zipkin
     * @param id
     * @return
     */
    @GetMapping("/consumer/hystrix/zipkin/{id}")
    public String zipkin(@PathVariable("id") Integer id) {
        return hystrixService.zipkin(id);
    }
}
