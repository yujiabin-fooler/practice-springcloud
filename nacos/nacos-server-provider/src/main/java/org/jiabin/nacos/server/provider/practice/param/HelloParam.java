package org.jiabin.nacos.server.provider.practice.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户实体类
 * @author jiabin.yu
 * @Date: 2020/12/2
 */
@Data
public class HelloParam implements Serializable {

    private static final long serialVersionUID = 2958405318099108563L;

    /**
     * 用户名
     */
    private String name;
}
