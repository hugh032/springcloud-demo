package com.golden.cloud.service.impl;

import com.golden.cloud.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class HystrixServiceImpl implements HystrixService {
    @Override
    public String ok(Integer id) {
        return Thread.currentThread().getName() + " ok ,id=" + id + "\t";
    }

    @Override
    @HystrixCommand(fallbackMethod = "timeoutFallback", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds" ,value = "3000")
    })
    public String timeout(Integer id) {
        int sleepNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(sleepNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + " timeout ,id=" + id + "\t"+"cost="+sleepNumber+"(s)";
    }

    /**
     * fallback 方法需要和对应方法同参
     * @param id
     * @return
     */
    public String timeoutFallback(Integer id) {
        return Thread.currentThread().getName()+" timeout fallback..";
    }
}
