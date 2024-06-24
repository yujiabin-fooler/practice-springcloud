package org.jiabin.seata.tcc.stock.prctice.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author wzy
 */
@LocalTCC
public interface StockTccDeductService {

    /**
     * try阶段
     */
    @TwoPhaseBusinessAction(name = "stockDeduct", commitMethod = "commit", rollbackMethod = "rollback")
    Integer stockDeduct(BusinessActionContext businessActionContext,
                        @BusinessActionContextParameter(paramName = "productId") Integer productId,
                        @BusinessActionContextParameter(paramName = "count") Integer count);

    /**
     * 提交事务
     */
    boolean commit(BusinessActionContext businessActionContext);

    /**
     * 回滚事务
     */
    boolean rollback(BusinessActionContext businessActionContext);
}
