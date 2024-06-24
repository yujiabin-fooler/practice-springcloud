package org.jiabin.seata.tcc.account.practice.param;

import lombok.Data;

@Data
public class AccountDeductParam {

    private Integer userId;
    private Integer amount;
}
