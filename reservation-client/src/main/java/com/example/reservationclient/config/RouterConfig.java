package com.example.reservationclient.config;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class RouterConfig {

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(5, 7);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder
                .routes()
                .route(routeSpec -> routeSpec
                        .host("*.foo.ru").and().path("/proxy")
                        .filters(filterSpec -> filterSpec
                                .setPath("/reservations")
                                .addRequestHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())))
                        .uri("http://localhost:8080"))
                .build();
    }
}
