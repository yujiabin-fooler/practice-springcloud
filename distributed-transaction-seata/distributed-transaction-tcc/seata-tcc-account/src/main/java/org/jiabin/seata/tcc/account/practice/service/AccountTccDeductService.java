package org.jiabin.seata.tcc.account.practice.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author wzy
 */
@LocalTCC
public interface AccountTccDeductService {

    /**
     * try阶段
     */
    @TwoPhaseBusinessAction(name = "deductAmount", commitMethod = "commit", rollbackMethod = "rollback")
    Integer deductAmount(BusinessActionContext businessActionContext,
                         @BusinessActionContextParameter(paramName = "userId") Integer userId,
                         @BusinessActionContextParameter(paramName = "amount") Integer amount);

    /**
     * 提交事务
     */
    boolean commit(BusinessActionContext businessActionContext);

    /**
     * 回滚事务
     */
    boolean rollback(BusinessActionContext businessActionContext);
}
