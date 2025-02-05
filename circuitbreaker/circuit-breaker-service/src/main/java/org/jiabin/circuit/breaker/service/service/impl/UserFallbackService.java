package org.jiabin.circuit.breaker.service.service.impl;

import org.jiabin.circuit.breaker.service.domain.CommonResult;
import org.jiabin.circuit.breaker.service.domain.User;
import org.jiabin.circuit.breaker.service.service.UserService;
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
        return new CommonResult<>(defaultUser,"服务降级返回",200);
    }

    @Override
    public CommonResult<User> getUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser,"服务降级返回",200);
    }

    @Override
    public CommonResult<User> getByUsername(String username) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser,"服务降级返回",200);
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
