package org.jiabin.seata.tcc.order.practice.service;

import org.jiabin.seata.tcc.order.practice.param.OrderCreateParam;

public interface OrderService {
    /**
     * 创建订单
     */
    int createOrder(OrderCreateParam orderCreateParam);
}
