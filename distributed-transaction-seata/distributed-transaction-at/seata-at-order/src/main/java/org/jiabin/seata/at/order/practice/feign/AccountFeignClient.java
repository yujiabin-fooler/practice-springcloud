package org.jiabin.seata.at.order.practice.feign;

import org.jiabin.seata.at.order.practice.feign.param.AccountDeductParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "account-server")
public interface AccountFeignClient {

    @PostMapping("/account/inter/deductAmount")
    Integer deductAmount(@RequestBody AccountDeductParam accountDeductParam);
}
