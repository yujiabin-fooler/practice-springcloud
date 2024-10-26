package org.jiabin.sa.token.api.practice.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.jiabin.sa.token.common.practice.api.CommonResult;
import org.jiabin.sa.token.common.practice.domain.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiabin.yu
 * @description 获取登录用户信息接口
 * @date 2020/6/19
 * 
 */
@RestController
@RequestMapping("/user")
public class UserController{

    @GetMapping("/info")
    public CommonResult<UserDTO> userInfo() {
        UserDTO userDTO = (UserDTO) StpUtil.getSession().get("userInfo");
        return CommonResult.success(userDTO);
    }

}
