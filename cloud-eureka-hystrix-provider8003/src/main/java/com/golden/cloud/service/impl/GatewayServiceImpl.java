package com.golden.cloud.service.impl;

import com.golden.cloud.common.ApiResult;
import com.golden.cloud.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class GatewayServiceImpl implements GatewayService {
    @Override
    public ApiResult t1(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ApiResult apiResult;
        try {
            ApiResult success = ApiResult.success(null);
            apiResult = success;
        } catch (Exception e) {
            log.error("GatewayServiceImpl  .... error:{}",e.getMessage());
            ApiResult error = ApiResult.error("-1", null, e.getMessage());
            return error;
        }
        return apiResult;
    }

}
