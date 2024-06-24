package org.jiabin.seata.at.stock.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.jiabin.seata.at.stock.practice.dao.StockMapper;
import org.jiabin.seata.at.stock.practice.entity.Stock;
import org.jiabin.seata.at.stock.practice.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Resource
    private StockMapper stockMapper;

    @Override
    public Integer deduct(Integer productId, Integer count) {
        LambdaUpdateWrapper<Stock> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper.eq(Stock::getProductId, productId);
        updateWrapper.setSql("count = count - " + count);
        updateWrapper.ge(Stock::getCount, count);

        return stockMapper.update(null, updateWrapper);
    }
}
