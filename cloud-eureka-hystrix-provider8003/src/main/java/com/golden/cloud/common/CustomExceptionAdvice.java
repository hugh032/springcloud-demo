package com.golden.cloud.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionAdvice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiResult handleBindException(MethodArgumentNotValidException e) {
        log.info("全局异常拦截：{}", e.getMessage());
        ApiResult result = new ApiResult("-1", null, e.getBindingResult().getFieldError().getDefaultMessage());
        return result;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ApiResult handleDataException(Exception e) {
        log.info("全局异常拦截：{}", e.getMessage());
        ApiResult result = new ApiResult("-1", null, e.getMessage());
        return result;
    }
}
