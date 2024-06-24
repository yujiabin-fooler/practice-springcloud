package org.jiabin.seata.tcc.stock.prctice.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.jiabin.seata.tcc.stock.prctice.dao.StockMapper;
import org.jiabin.seata.tcc.stock.prctice.entity.Stock;
import org.jiabin.seata.tcc.stock.prctice.service.StockTccDeductService;
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
public class StockTccDeductServiceImpl implements StockTccDeductService {

    @Resource
    private StockMapper stockMapper;

    @Override
    public Integer stockDeduct(BusinessActionContext businessActionContext, Integer productId, Integer count) {
        LambdaUpdateWrapper<Stock> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper.eq(Stock::getProductId, productId);
        updateWrapper.setSql("count = count - " + count);
        updateWrapper.ge(Stock::getCount, count);

        return stockMapper.update(null, updateWrapper);
    }

    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        Integer productId = businessActionContext.getActionContext("productId", Integer.class);
        Integer count = businessActionContext.getActionContext("count", Integer.class);

        log.info("扣减库存事务提交， 商品id:{}, 扣减数量:{}", productId, count);
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {

        Integer productId = businessActionContext.getActionContext("productId", Integer.class);
        Integer count = businessActionContext.getActionContext("count", Integer.class);
        LambdaUpdateWrapper<Stock> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper.eq(Stock::getProductId, productId);
        updateWrapper.setSql("count = count + " + count);
        updateWrapper.ge(Stock::getCount, count);

        log.info("扣减库存事务回滚， 商品id:{}, 扣减数量:{}", productId, count);

        return stockMapper.update(null, updateWrapper) > 0;
    }
}
