package org.jiabin.seata.tcc.order.practice.feign.param;

import lombok.Data;

@Data
public class AccountDeductParam {

    private Integer userId;
    private Integer amount;
}
