package org.jiabin.seata.tcc.order.practice.param;

import lombok.Data;

@Data
public class OrderCreateParam {
    private Integer id;
    private Integer productId;
    private Integer userId;
    private Integer payAmount;
    private Integer count;
}
