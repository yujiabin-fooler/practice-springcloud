package org.jiabin.seata.at.stock.practice.controller;

import org.jiabin.seata.at.stock.practice.param.StockDeductParam;
import org.jiabin.seata.at.stock.practice.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/stock/inter")
public class StockInterController {

    @Resource
    private StockService stockService;

    @PostMapping("/deductCount")
    public Integer deductCount(@RequestBody StockDeductParam stockDeductParam) {
        Integer deductResult = stockService.deduct(stockDeductParam.getProductId(),
                stockDeductParam.getCount());
        if (deductResult <= 0) {
            throw new RuntimeException("商品库存扣减失败");
        }

        return deductResult;
    }

}
