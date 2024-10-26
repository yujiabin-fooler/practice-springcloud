package org.jiabin.nacos.loadbalancer.service.practice.controller;

import org.jiabin.nacos.loadbalancer.service.practice.domain.CommonResult;
import org.jiabin.nacos.loadbalancer.service.practice.domain.User;
import org.jiabin.nacos.loadbalancer.service.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiabin.yu
 * @description 通过OpenFeign远程调用user-service的Controller
 * @date 2023/11/30
 * 
 */
@RestController
@RequestMapping("/userFeign")
public class UserFeignController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public CommonResult getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/getByUsername")
    public CommonResult getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
