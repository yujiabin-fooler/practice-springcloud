package org.jiabin.seata.tcc.order.practice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_order")
@Data
public class Order {
    
    private Integer id;

    private Integer userId;

    private Integer productId;

    private Integer count;

    private Integer payAmount;
}
