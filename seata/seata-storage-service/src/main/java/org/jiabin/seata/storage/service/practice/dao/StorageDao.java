package org.jiabin.seata.storage.service.practice.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jiabin.yu
 * @description 库存管理Dao
 * @date 2023/12/8
 * 
 */
@Repository
public interface StorageDao {

    /**
     * 扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
