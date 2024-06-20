package org.jiabin.gateway.filter.practice.controller;

import cn.hutool.json.JSONUtil;
import org.jiabin.gateway.filter.practice.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试接口控制层
 * @Author: junqiang.lu
 * @Date: 2021/10/22
 */
@Slf4j
@RestController
public class DemoController {

    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping(value = "/api/shop/user/list")
    public ResponseEntity<?> list(){
        log.info("/api/user/list");
        return ResponseEntity.ok(ApiResult.success("请求成功啦!!!"));
    }

    /**
     * 通知
     *
     * @param obj
     * @return
     */
    @PostMapping(value = "/api/shop/user/notify")
    public ResponseEntity<?> notify(@RequestBody Object obj) {
        log.warn("/api/user/notify, request param: {}", JSONUtil.toJsonStr(obj));
        return ResponseEntity.ok("Skywalking warning notify");
    }

    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping(value = "/api/shop/user/login")
    public ResponseEntity<ApiResult> login() {
        log.info("用户登录");
        return ResponseEntity.ok(ApiResult.success());
    }

    /**
     * h5-主页
     *
     * @return
     */
    @PostMapping(value = "/res/h5/index")
    public ResponseEntity<ApiResult> h5Index() {
        log.info("h5");
        return ResponseEntity.ok(ApiResult.success());
    }



}
