package org.jiabin.seata.storage.service.practice.service;

/**
 * @author jiabin.yu
 * @description 库存管理Service
 * @date 2023/12/8
 * 
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
