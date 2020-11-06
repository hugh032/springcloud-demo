package com.golden.cloud.contorller;

import com.golden.cloud.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@SuppressWarnings("all")
public class TestController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private DiscoveryClient discoveryClient;
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/discovery")
    public Object test() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
            logger.info("service ：{}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-EUREKA-PROVIDER8001");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.toString());
            logger.info(instances.toString());
        }
        return this.discoveryClient;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello"+port;
    }

    @GetMapping("/timeout/hello")
    public String timeoutHello() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello" + port;
    }
    @GetMapping("/helloWithQueryMap")
    public String helloWithQueryMap(@SpringQueryMap User user) {
        return user.toString();
    }
}
