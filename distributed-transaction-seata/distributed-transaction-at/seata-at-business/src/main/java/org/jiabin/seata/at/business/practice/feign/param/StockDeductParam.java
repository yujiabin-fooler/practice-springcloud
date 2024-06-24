package org.jiabin.seata.at.business.practice.feign.param;

import lombok.Data;

@Data
public class StockDeductParam {
    private Integer productId;
    private Integer count;
}
