package org.jiabin.skywalking.feign.service.practice.service;

import org.jiabin.skywalking.feign.service.practice.domain.CommonResult;
import org.jiabin.skywalking.feign.service.practice.domain.User;

/**
 * @author jiabin.yu
 * @description 自定义链路跟踪Service
 * @date 2024/1/4
 * 
 */
public interface CustomTraceService {

    CommonResult<User> annotation(Long userId);

    CommonResult<User> activeSpan(Long userId);

    CommonResult<User> openTracing(Long userId);
}
