package org.jiabin.skywalking.feign.service.practice.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import org.jiabin.skywalking.feign.service.practice.domain.CommonResult;
import org.jiabin.skywalking.feign.service.practice.domain.User;
import org.jiabin.skywalking.feign.service.practice.service.CustomTraceService;
import org.jiabin.skywalking.feign.service.practice.service.UserService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiabin.yu
 * @description 自定义链接跟踪Service实现类
 * @date 2024/1/4
 * 
 */
@Service
public class CustomTraceServiceImpl implements CustomTraceService {

    @Autowired
    private UserService userService;

    @Trace
    @Tag(key = "userId", value = "arg[0]")//arg[0]为固定写法
    @Tag(key = "result.id", value = "returnedObj.data.id")//returnedObj为固定写法
    @Tag(key = "result.username", value = "returnedObj.data.username")
    @Override
    public CommonResult<User> annotation(Long userId) {
        return userService.getUser(userId);
    }

    @Override
    public CommonResult<User> activeSpan(Long userId) {
        ActiveSpan.debug("activeSpan debug message...");
        ActiveSpan.info("activeSpan info message...");
        ActiveSpan.error("activeSpan error message...");
        return userService.getUser(userId);
    }

    @Override
    public CommonResult<User> openTracing(Long userId) {
        //模拟一个耗时操作
        ThreadUtil.safeSleep(1000);
        //只监控startManual和finish之间的代码片段
        Tracer tracer = new SkywalkingTracer();
        Tracer.SpanBuilder spanBuilder = tracer.buildSpan("/trace-span/openTracing");
        Span span = spanBuilder.withTag("tag", "openTracing").startManual();
        CommonResult<User> result = userService.getUser(userId);
        span.finish();
        return result;
    }
}
