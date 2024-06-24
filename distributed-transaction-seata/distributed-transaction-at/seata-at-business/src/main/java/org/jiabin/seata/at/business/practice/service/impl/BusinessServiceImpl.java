package org.jiabin.seata.at.business.practice.service.impl;

import org.jiabin.seata.at.business.practice.feign.OrderFeignClient;
import org.jiabin.seata.at.business.practice.feign.StockFeignClient;
import org.jiabin.seata.at.business.practice.feign.param.OrderCreateParam;
import org.jiabin.seata.at.business.practice.feign.param.StockDeductParam;
import org.jiabin.seata.at.business.practice.param.MakeOrderParam;
import org.jiabin.seata.at.business.practice.service.BusinessService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wzy
 */
@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private StockFeignClient stockFeignClient;
    @Resource
    private OrderFeignClient orderFeignClient;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public String makeOrder(MakeOrderParam makeOrderParam) {

        // 1、先扣减库存
        StockDeductParam stockDeductParam = new StockDeductParam();

        stockDeductParam.setProductId(makeOrderParam.getProductId());
        stockDeductParam.setCount(makeOrderParam.getCount());

        stockFeignClient.deductCount(stockDeductParam);

        // 2、创建订单
        OrderCreateParam orderCreateParam = new OrderCreateParam();
        BeanUtils.copyProperties(makeOrderParam, orderCreateParam);
        orderFeignClient.createOrder(orderCreateParam);

        return "SUCCESS";
    }
}
