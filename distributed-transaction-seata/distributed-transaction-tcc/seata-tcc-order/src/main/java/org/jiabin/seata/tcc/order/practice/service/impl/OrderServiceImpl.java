package org.jiabin.seata.tcc.order.practice.service.impl;

import org.jiabin.seata.tcc.order.practice.dao.OrderMapper;
import org.jiabin.seata.tcc.order.practice.feign.AccountFeignClient;
import org.jiabin.seata.tcc.order.practice.feign.param.AccountDeductParam;
import org.jiabin.seata.tcc.order.practice.param.OrderCreateParam;
import org.jiabin.seata.tcc.order.practice.service.OrderCreateTccService;
import org.jiabin.seata.tcc.order.practice.service.OrderService;
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

    @Resource
    private OrderCreateTccService orderCreateTccService;

    @Override
    public int createOrder(OrderCreateParam orderCreateParam) {


        // 1、 先扣减用户金额
        AccountDeductParam accountDeductParam = new AccountDeductParam();
        accountDeductParam.setUserId(orderCreateParam.getUserId());
        accountDeductParam.setAmount(orderCreateParam.getPayAmount());

        accountFeignClient.deductAmount(accountDeductParam);

        // 2、创建订单
        return orderCreateTccService.createOrder(null, orderCreateParam);
    }
}
