package org.jiabin.gateway.filter.practice.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 网关路由白名单查询单条
 * @author jiabin.yu
 * @Date: 2022/8/23
 */
@Data
public class WhiteListInfoParam implements Serializable {

    private static final long serialVersionUID = -5392392668785473906L;

    /**
     * id
     */
    private Long id;


}
