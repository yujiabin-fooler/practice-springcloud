package org.jiabin.knife4j.user.practice.controller;

import org.jiabin.knife4j.user.practice.domain.CommonResult;
import org.jiabin.knife4j.user.practice.domain.User;
import org.jiabin.knife4j.user.practice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jiabin.yu
 * @description 用户管理Controller
 * @date 2023/11/29
 * 
 */
@Tag(name = "UserController",description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Operation(summary = "添加用户")
    @PostMapping("/create")
    public CommonResult create(@RequestBody User user) {
        userService.create(user);
        return new CommonResult("操作成功", 200);
    }

    @Operation(summary = "根据ID查询用户")
    @GetMapping("/{id}")
    public CommonResult<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        LOGGER.info("根据id获取用户信息，用户名称为：{}", user.getUsername());
        return new CommonResult<>(user);
    }

    @Operation(summary = "根据ID查询多个用户")
    @GetMapping("/getUserByIds")
    public CommonResult<List<User>> getUserByIds(@RequestParam List<Long> ids) {
        List<User> userList = userService.getUserByIds(ids);
        LOGGER.info("根据ids获取用户信息，用户列表为：{}", userList);
        return new CommonResult<>(userList);
    }

    @Operation(summary = "根据用户名查询用户")
    @GetMapping("/getByUsername")
    public CommonResult<User> getByUsername(@RequestParam String username) {
        User user = userService.getByUsername(username);
        return new CommonResult<>(user);
    }

    @Operation(summary = "修改用户")
    @PostMapping("/update")
    public CommonResult update(@RequestBody User user) {
        userService.update(user);
        return new CommonResult("操作成功", 200);
    }

    @Operation(summary = "根据ID删除用户")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        userService.delete(id);
        return new CommonResult("操作成功", 200);
    }
}
