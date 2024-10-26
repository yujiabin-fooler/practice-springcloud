package org.jiabin.skywalking.feign.service.practice.controller;

import org.jiabin.skywalking.feign.service.practice.domain.CommonResult;
import org.jiabin.skywalking.feign.service.practice.domain.User;
import org.jiabin.skywalking.feign.service.practice.service.CustomTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiabin.yu
 * @description 自定义链路跟踪测试Controller
 * @date 2024/1/4
 * 
 */
@RestController
@RequestMapping("/trace")
public class CustomTraceController {

    @Autowired
    private CustomTraceService customTraceService;

    @GetMapping("/annotation")
    public CommonResult<User> annotation(@RequestParam Long userId) {
        return customTraceService.annotation(userId);
    }

    @GetMapping("/activeSpan")
    public CommonResult<User> activeSpan(@RequestParam Long userId) {
        return customTraceService.activeSpan(userId);
    }

    @GetMapping("/openTracing")
    public CommonResult<User> openTracing(@RequestParam Long userId) {
        return customTraceService.openTracing(userId);
    }
}
