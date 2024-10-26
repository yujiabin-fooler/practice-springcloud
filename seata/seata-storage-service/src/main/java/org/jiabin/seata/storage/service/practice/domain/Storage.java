package org.jiabin.seata.storage.service.practice.domain;

import lombok.Data;

/**
 * @author jiabin.yu
 * @description 库存管理实体类
 * @date 2023/12/8
 * 
 */
@Data
public class Storage {

    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
