package com.golden.cloud.controller;

import com.golden.cloud.common.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/incaseoffailureusethis")
    public ApiResult error() {
        return ApiResult.error("gateway fallback.....");
    }
}
