package org.jiabin.gateway.filter.practice.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 网关路由白名单删除单条
 * @author jiabin.yu
 * @Date: 2022/8/23
 */
@Data
public class WhiteListDeleteParam implements Serializable {

    private static final long serialVersionUID = 7390107925356306269L;

    /**
     * id
     */
    private Long id;

}
