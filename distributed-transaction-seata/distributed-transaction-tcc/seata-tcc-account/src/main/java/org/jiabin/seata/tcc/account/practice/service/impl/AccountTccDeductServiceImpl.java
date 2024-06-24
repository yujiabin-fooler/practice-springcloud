package org.jiabin.seata.tcc.account.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.jiabin.seata.tcc.account.practice.dao.AccountMapper;
import org.jiabin.seata.tcc.account.practice.entity.Account;
import org.jiabin.seata.tcc.account.practice.service.AccountTccDeductService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 库存扣减方法
 *
 * @author wzy
 */
@Slf4j
@Service
public class AccountTccDeductServiceImpl implements AccountTccDeductService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Integer deductAmount(BusinessActionContext businessActionContext, Integer userId, Integer amount) {
        LambdaUpdateWrapper<Account> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper.eq(Account::getUserId, userId);
        updateWrapper.ge(Account::getAmount, amount);
        updateWrapper.setSql("amount = amount - " + amount);

        return accountMapper.update(null, updateWrapper);
    }

    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        Integer userId = businessActionContext.getActionContext("userId", Integer.class);
        Integer amount = businessActionContext.getActionContext("amount", Integer.class);

        log.info("扣减用户余额事务提交， 用户id:{}, 扣减金额:{}", userId, amount);
        return false;
    }

    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {

//        Integer userId = businessActionContext.getActionContext("userId", Integer.class);
//        Integer amount = businessActionContext.getActionContext("amount", Integer.class);
//
//        LambdaUpdateWrapper<Account> updateWrapper = new LambdaUpdateWrapper<>();
//
//        updateWrapper.eq(Account::getUserId, userId);
//        updateWrapper.ge(Account::getAmount, amount);
//        updateWrapper.setSql("amount = amount + " + amount);
//
//        log.info("扣减库存事务回滚， 商品id:{}, 扣减金额:{}", userId, amount);
//
//        return accountMapper.update(null, updateWrapper) > 0;

        return true;
    }
}
