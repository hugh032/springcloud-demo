package com.golden.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return  builder.routes()
                .route("path_route",r->r.path("/hystrix/{x}/**")
                    .uri("http://localhost:8003"))
                .route("host_route",r->r.host("localhost")
                    .uri("http://httpbin.org"))
                .build();
    }
}
