package org.jiabin.seata.tcc.order.practice.service;

import org.jiabin.seata.tcc.order.practice.param.OrderCreateParam;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

public interface OrderCreateTccService {
    /**
     * try阶段
     */
    @TwoPhaseBusinessAction(name = "createOrder", commitMethod = "commit", rollbackMethod = "rollback")
    Integer createOrder(BusinessActionContext businessActionContext,
                        @BusinessActionContextParameter(paramName = "orderCreateParam") OrderCreateParam orderCreateParam);

    /**
     * 提交事务
     */
    boolean commit(BusinessActionContext businessActionContext);

    /**
     * 回滚事务
     */
    boolean rollback(BusinessActionContext businessActionContext);
}
