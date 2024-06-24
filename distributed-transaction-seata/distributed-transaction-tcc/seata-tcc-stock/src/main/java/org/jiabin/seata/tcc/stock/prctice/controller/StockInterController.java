package org.jiabin.seata.tcc.stock.prctice.controller;

import org.jiabin.seata.tcc.stock.prctice.param.StockDeductParam;
import org.jiabin.seata.tcc.stock.prctice.service.StockTccDeductService;
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
    private StockTccDeductService stockTccDeductService;

    @PostMapping("/deductCount")
    public Integer deductCount(@RequestBody StockDeductParam stockDeductParam) {
        Integer deductResult = stockTccDeductService.stockDeduct(null, stockDeductParam.getProductId(),
                stockDeductParam.getCount());
        if (deductResult <= 0) {
            throw new RuntimeException("商品库存扣减失败");
        }

        return deductResult;
    }

}
