package org.seata.account.service.practice.controller;

import org.seata.account.service.practice.domain.CommonResult;
import org.seata.account.service.practice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author  jiabin.yu
 * @description 账户管理Controller
 * @date 2023/12/8
 *
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        accountService.decrease(userId,money);
        return new CommonResult("扣减账户余额成功！",200);
    }
}
