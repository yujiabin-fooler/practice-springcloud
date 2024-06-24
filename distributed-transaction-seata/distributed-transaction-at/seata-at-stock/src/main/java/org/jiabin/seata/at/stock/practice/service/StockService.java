package org.jiabin.seata.at.stock.practice.service;

public interface StockService {

    Integer deduct(Integer productId, Integer count);
}
