package org.jiabin.seata.tcc.business.practice.feign;

import org.jiabin.seata.tcc.business.practice.feign.param.StockDeductParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "stock-server")
public interface StockFeignClient {

    @PostMapping("/stock/inter/deductCount")
    Integer deductCount(@RequestBody StockDeductParam stockDeductParam);
}
