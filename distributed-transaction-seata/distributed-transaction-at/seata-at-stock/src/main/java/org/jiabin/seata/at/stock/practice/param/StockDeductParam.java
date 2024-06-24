package org.jiabin.seata.at.stock.practice.param;

import lombok.Data;

@Data
public class StockDeductParam {
    private Integer productId;
    private Integer count;
}
