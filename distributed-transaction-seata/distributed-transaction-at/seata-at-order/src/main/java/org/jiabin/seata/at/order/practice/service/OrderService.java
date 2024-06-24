package org.jiabin.seata.at.order.practice.service;

import org.jiabin.seata.at.order.practice.param.OrderCreateParam;

public interface OrderService {
    /**
     * 创建订单
     */
    int createOrder(OrderCreateParam orderCreateParam);
}
