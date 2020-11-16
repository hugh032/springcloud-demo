package com.golden.cloud.service;

import org.springframework.stereotype.Service;

@Service
public interface HystrixService {

    String ok(Integer id);

    String timeout(Integer id);


    String zipkin(Integer id);
}
