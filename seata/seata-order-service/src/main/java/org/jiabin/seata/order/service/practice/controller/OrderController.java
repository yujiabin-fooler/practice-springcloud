package org.jiabin.seata.order.service.practice.controller;

import org.jiabin.seata.order.service.practice.domain.CommonResult;
import org.jiabin.seata.order.service.practice.domain.Order;
import org.jiabin.seata.order.service.practice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiabin.yu 
 * @description 订单管理Controller
 * @date 2023/12/8
 * 
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult("订单创建成功!", 200);
    }
}
