package org.jiabin.order.module.practice.controller;

import org.jiabin.common.practice.api.ApiResult;
import org.jiabin.user.client.practice.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author jiabin.yu
 * @CreateTime: 2023-07-15  08:51
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserClient userClient;

    @Value("${server.port}")
    private String port;
    @Value("${spring.cloud.nacos.discovery.metadata.version}")
    private String mateVersion;

    @GetMapping("/{orderNo}")
    public ApiResult getOrderInfo(@PathVariable("orderNo") String orderNo){
        Map<String, String> resultData = initData().get(orderNo);
        resultData.put("port",port);
        resultData.put("metaVersion",mateVersion);
        if(resultData != null){
            ApiResult<String> apiResult = userClient.getUserName(resultData.get("userNo"));
            resultData.put("userName",apiResult.getData());
        }
        return ApiResult.success(resultData);
    }

    private Map<String,Map<String,String>> initData(){
        Map<String,Map<String,String>> orderMap = new HashMap<>();
        Map<String, String> orderInfo1 = new LinkedHashMap<>();
        orderInfo1.put("orderNo","N0001");
        orderInfo1.put("goodsName","商品-G0001");
        orderInfo1.put("userNo","U0001");
        orderMap.put("N0001",orderInfo1);
        Map<String, String> orderInfo2 = new LinkedHashMap<>();
        orderInfo2.put("orderNo","N0002");
        orderInfo2.put("goodsName","商品-G0002");
        orderInfo2.put("userNo","U0002");
        orderMap.put("N0002",orderInfo2);
        return orderMap;
    }
}
