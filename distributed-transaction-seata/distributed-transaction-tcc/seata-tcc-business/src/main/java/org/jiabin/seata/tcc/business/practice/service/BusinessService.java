package org.jiabin.seata.tcc.business.practice.service;

import org.jiabin.seata.tcc.business.practice.param.MakeOrderParam;

public interface BusinessService {
    String makeOrder(MakeOrderParam makeOrderParam);
}
