package org.jiabin.seata.order.service.practice.dao;

import org.jiabin.seata.order.service.practice.domain.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jiabin.yu 
 * @description 订单管理Dao
 * @date 2023/12/8
 * 
 */
@Repository
public interface OrderDao {

    /**
     * 创建订单
     */
    void create(Order order);

    /**
     * 修改订单金额
     */
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
