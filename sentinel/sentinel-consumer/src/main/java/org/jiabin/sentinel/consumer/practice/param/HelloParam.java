package org.jiabin.sentinel.consumer.practice.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户实体类
 * @author jiabin.yu
 * @Date: 2020/12/2
 */
@Data
public class HelloParam implements Serializable {

    private static final long serialVersionUID = -686738885661126900L;

    /**
     * 用户名
     */
    private String name;
}
