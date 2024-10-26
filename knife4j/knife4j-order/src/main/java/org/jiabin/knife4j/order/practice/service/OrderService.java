package org.jiabin.knife4j.order.practice.service;

import org.jiabin.knife4j.order.practice.domain.Order;

/**
 * @author jiabin.yu
 * @description 订单Service
 * @date 2023/11/29
 * 
 */
public interface OrderService {
    void create(Order order);

    Order getOrder(Long id);

    void update(Order order);

    void delete(Long id);
}
