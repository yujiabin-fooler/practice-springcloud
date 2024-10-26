package org.jiabin.seata.order.service.practice.service.impl;

import org.jiabin.seata.order.service.practice.dao.OrderDao;
import org.jiabin.seata.order.service.practice.domain.Order;
import org.jiabin.seata.order.service.practice.service.AccountService;
import org.jiabin.seata.order.service.practice.service.OrderService;
import org.jiabin.seata.order.service.practice.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiabin.yu 
 * @description 订单业务实现类
 * @date 2023/12/8
 * 
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */
    @Override
    @GlobalTransactional
    public void create(Order order) {
        LOGGER.info("------->下单开始");
        //本应用创建订单
        orderDao.create(order);

        //远程调用库存服务扣减库存
        LOGGER.info("------->order-service中扣减库存开始");
        storageService.decrease(order.getProductId(),order.getCount());
        LOGGER.info("------->order-service中扣减库存结束");

        //远程调用账户服务扣减余额
        LOGGER.info("------->order-service中扣减余额开始");
        accountService.decrease(order.getUserId(),order.getMoney());
        LOGGER.info("------->order-service中扣减余额结束");

        //修改订单状态为已完成
        LOGGER.info("------->order-service中修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        LOGGER.info("------->order-service中修改订单状态结束");

        LOGGER.info("------->下单结束");
    }
}
