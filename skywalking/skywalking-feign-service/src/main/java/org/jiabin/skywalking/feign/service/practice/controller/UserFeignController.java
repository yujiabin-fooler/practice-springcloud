package org.jiabin.skywalking.feign.service.practice.controller;

import org.jiabin.skywalking.feign.service.practice.domain.CommonResult;
import org.jiabin.skywalking.feign.service.practice.domain.User;
import org.jiabin.skywalking.feign.service.practice.service.UserService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiabin.yu
 * @description OpenFeign远程调用Controller
 * @date 2023/12/6
 * 
 */
@RestController
@RequestMapping("/userFeign")
public class UserFeignController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public CommonResult<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/getByUsername")
    public CommonResult<User> getByUsername(@RequestParam String username) {
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
