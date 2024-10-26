package org.jiabin.knife4j.order.practice.controller;

import org.jiabin.knife4j.order.practice.domain.CommonResult;
import org.jiabin.knife4j.order.practice.domain.Order;
import org.jiabin.knife4j.order.practice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiabin.yu
 * @description 订单管理Controller
 * @date 2023/11/29
 * 
 */
@Tag(name = "OrderController",description = "订单管理")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "添加订单")
    @PostMapping("/create")
    public CommonResult create(@RequestBody Order order) {
        orderService.create(order);
        return new CommonResult("操作成功", 200);
    }

    @Operation(summary = "根据ID获取订单")
    @GetMapping("/{id}")
    public CommonResult<Order> getUser(@PathVariable Long id) {
        Order order = orderService.getOrder(id);
        return new CommonResult<>(order);
    }

    @Operation(summary = "修改订单")
    @PostMapping("/update")
    public CommonResult update(@RequestBody Order order) {
        orderService.update(order);
        return new CommonResult("操作成功", 200);
    }

    @Operation(summary = "根据ID删除订单")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        orderService.delete(id);
        return new CommonResult("操作成功", 200);
    }
}
