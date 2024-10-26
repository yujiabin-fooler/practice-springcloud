package org.jiabin.knife4j.order.practice.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;

/**
 * @author jiabin.yu
 * @description 订单实体类
 * @date 2023/11/29
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Order {
    @Schema(title = "订单ID")
    private Long id;
    @Schema(title = "用户ID")
    private Long userId;
    @Schema(title = "商品ID")
    private Long productId;
    @Schema(title = "商品数量")
    private Integer count;
    @Schema(title = "商品单价")
    private BigDecimal money;
    @Schema(title = "订单状态")
    private Integer status;

    @Tolerate
    public Order(){}
}
