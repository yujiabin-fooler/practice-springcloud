package org.jiabin.nacos.loadbalancer.service.practice.service;

import org.jiabin.nacos.loadbalancer.service.practice.domain.CommonResult;
import org.jiabin.nacos.loadbalancer.service.practice.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiabin.yu
 * @description OpenFeign远程调用user-service的业务类
 * @date 2023/11/30
 * 
 */
@FeignClient(value = "nacos-user-service")
public interface UserService {
    @PostMapping("/user/create")
    CommonResult create(@RequestBody User user);

    @GetMapping("/user/{id}")
    CommonResult<User> getUser(@PathVariable("id") Long id);

    @GetMapping("/user/getByUsername")
    CommonResult<User> getByUsername(@RequestParam("username") String username);

    @PostMapping("/user/update")
    CommonResult update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    CommonResult delete(@PathVariable("id") Long id);
}
