package org.jiabin.seata.at.account.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.jiabin.seata.at.account.practice.dao.AccountMapper;
import org.jiabin.seata.at.account.practice.entity.Account;
import org.jiabin.seata.at.account.practice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Integer deduct(Integer userId, Integer amount) {
        LambdaUpdateWrapper<Account> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper.eq(Account::getUserId, userId);
        updateWrapper.ge(Account::getAmount, amount);
        updateWrapper.setSql("amount = amount - " + amount);

        return accountMapper.update(null, updateWrapper);
    }
}
