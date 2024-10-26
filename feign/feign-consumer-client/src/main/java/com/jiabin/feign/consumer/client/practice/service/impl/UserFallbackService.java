package com.jiabin.feign.consumer.client.practice.service.impl;

import com.jiabin.feign.consumer.client.practice.domain.CommonResult;
import com.jiabin.feign.consumer.client.practice.domain.User;
import com.jiabin.feign.consumer.client.practice.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author jiabin.yu
 * @description UserService中的服务降级处理逻辑
 * @date 2023/12/6
 * 
 */
@Component
public class UserFallbackService implements UserService {
    @Override
    public CommonResult create(User user) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }

    @Override
    public CommonResult<User> getUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }

    @Override
    public CommonResult<User> getByUsername(String username) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }

    @Override
    public CommonResult update(User user) {
        return new CommonResult("调用失败，服务被降级",500);
    }

    @Override
    public CommonResult delete(Long id) {
        return new CommonResult("调用失败，服务被降级",500);
    }
}
