package org.jiabin.sa.token.api.practice.controller;

import org.jiabin.sa.token.common.practice.api.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiabin.yu
 * @description 测试接口
 * @date 2020/6/19
 * 
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public CommonResult hello() {
        return CommonResult.success("Hello World.");
    }

}
