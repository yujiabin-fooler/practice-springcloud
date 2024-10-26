package org.seata.account.service.practice.service;

import java.math.BigDecimal;

/**
 * @author  jiabin.yu
 * @description 账户管理Service
 * @date 2023/12/8
 *
 */
public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    void decrease(Long userId, BigDecimal money);
}
