package org.jiabin.seata.tcc.account.practice.controller;

import org.jiabin.seata.tcc.account.practice.param.AccountDeductParam;
import org.jiabin.seata.tcc.account.practice.service.AccountTccDeductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/account/inter")
public class AccountInterController {

    @Resource
    private AccountTccDeductService accountTccDeductService;

    @PostMapping("/deductAmount")
    public Integer deductAmount(@RequestBody AccountDeductParam accountDeductParam) {
        Integer deductResult = accountTccDeductService.deductAmount(null,
                accountDeductParam.getUserId(), accountDeductParam.getAmount());

        if (deductResult <= 0) {
            throw new RuntimeException("用户账户金额扣减失败");
        }

        return deductResult;
    }
}
