package org.jiabin.user.app.practice.controller;

import org.jiabin.common.practice.api.ApiResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author jiabin.yu
 * @CreateTime: 2023-07-15  08:57
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${server.port}")
    private String port;
    @Value("${spring.cloud.nacos.discovery.metadata.version}")
    private String metaVersion;

    private static final Map<String,String> userMap = new HashMap();
    static {
        userMap.put("U0001","用户1号");
        userMap.put("U0002","用户2号");
        userMap.put("U0003","用户3号");
    }


    @GetMapping("/{userNo}")
    public ApiResult<String> getUserName(@PathVariable("userNo") String userNo){
        return ApiResult.success(userMap.get(userNo) + " - port="+port + " - mateVersion="+metaVersion);
    }
}
