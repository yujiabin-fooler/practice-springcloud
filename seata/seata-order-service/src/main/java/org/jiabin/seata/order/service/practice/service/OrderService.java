package org.jiabin.seata.order.service.practice.service;


import org.jiabin.seata.order.service.practice.domain.Order;

/**
 * @author jiabin.yu 
 * @description 订单管理Service
 * @date 2023/12/8
 * 
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
