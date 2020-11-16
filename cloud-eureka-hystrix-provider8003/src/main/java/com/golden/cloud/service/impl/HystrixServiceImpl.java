package com.golden.cloud.service.impl;

import cn.hutool.http.HttpUtil;
import com.golden.cloud.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.cloud.netflix.ribbon.apache.HttpClientUtils;
import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

import java.util.concurrent.TimeUnit;

@Component
public class HystrixServiceImpl implements HystrixService {
    @Override
    public String ok(Integer id) {
        return Thread.currentThread().getName() + " ok ,id=" + id + "\t";
    }

    @Override
    @HystrixCommand(fallbackMethod = "timeoutFallback", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds" ,value = "7000")
    })
    public String timeout(Integer id) {
        int sleepNumber = 10;
        try {
            TimeUnit.SECONDS.sleep(sleepNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + " timeout ,id=" + id + "\t"+"cost="+sleepNumber+"(s)";
    }

    /**
     * zipkin测试方法
     * @param id
     * @return
     */
    @Override
    public String zipkin(Integer id) {
        String s = HttpUtil.get("https://www.baidu.com");
        return Thread.currentThread().getName() + " ok ,id=" + id + "\t" +s;
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
