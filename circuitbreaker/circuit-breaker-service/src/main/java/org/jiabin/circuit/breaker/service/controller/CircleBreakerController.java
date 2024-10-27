package org.jiabin.circuit.breaker.service.controller;

import org.jiabin.circuit.breaker.service.domain.CommonResult;
import org.jiabin.circuit.breaker.service.domain.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * @author jiabin.yu
 * @description CircleBreaker功能测试接口
 * @date 2023/12/7
 * 
 */
@RestController
@RequestMapping("/breaker")
public class CircleBreakerController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @GetMapping("/circleBreakerApi/{id}")
    @CircuitBreaker(name = "circleBreakerApi")
    public CommonResult circleBreakerApi(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    @GetMapping("/retryApi/{id}")
    @Retry(name = "retryApi",fallbackMethod = "fallbackAfterRetry")
    public CommonResult retryApi(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    @GetMapping("/rateLimiterApi/{id}")
    @RateLimiter(name = "rateLimiterApi")
    public CommonResult rateLimiterApi(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    @GetMapping("/timeLimiterApi/{id}")
    @TimeLimiter(name = "timeLimiterApi")
    public CompletableFuture<CommonResult> timeLimiterApi(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(this::callApiWithDelay);
    }

    public CommonResult fallbackAfterRetry(Long id,Exception exception) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser,exception.getMessage(),200);
    }

    public CommonResult callApiWithDelay() {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, 1);
    }

}
