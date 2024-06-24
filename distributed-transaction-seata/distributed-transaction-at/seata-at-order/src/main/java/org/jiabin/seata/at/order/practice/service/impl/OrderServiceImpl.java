package org.jiabin.seata.at.order.practice.service.impl;

import org.jiabin.seata.at.order.practice.dao.OrderMapper;
import org.jiabin.seata.at.order.practice.entity.Order;
import org.jiabin.seata.at.order.practice.feign.AccountFeignClient;
import org.jiabin.seata.at.order.practice.feign.param.AccountDeductParam;
import org.jiabin.seata.at.order.practice.param.OrderCreateParam;
import org.jiabin.seata.at.order.practice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AccountFeignClient accountFeignClient;

    @Override
    public int createOrder(OrderCreateParam orderCreateParam) {


        // 1、 先扣减用户金额
        AccountDeductParam accountDeductParam = new AccountDeductParam();
        accountDeductParam.setUserId(orderCreateParam.getUserId());
        accountDeductParam.setAmount(orderCreateParam.getPayAmount());

        accountFeignClient.deductAmount(accountDeductParam);

        // 2、创建订单
        Order order = new Order();

        order.setProductId(orderCreateParam.getProductId());
        order.setUserId(orderCreateParam.getUserId());
        order.setPayAmount(orderCreateParam.getPayAmount());
        order.setCount(orderCreateParam.getCount());

        return orderMapper.insert(order);
    }
}
