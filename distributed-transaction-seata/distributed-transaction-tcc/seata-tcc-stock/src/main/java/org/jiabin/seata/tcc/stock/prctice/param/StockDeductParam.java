package org.jiabin.seata.tcc.stock.prctice.param;

import lombok.Data;

@Data
public class StockDeductParam {
    private Integer productId;
    private Integer count;
}
