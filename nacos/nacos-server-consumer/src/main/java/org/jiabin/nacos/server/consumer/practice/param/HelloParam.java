package org.jiabin.nacos.server.consumer.practice.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户实体类
 * @Author: junqiang.lu
 * @Date: 2020/12/2
 */
@Data
public class HelloParam implements Serializable {

    private static final long serialVersionUID = -6118723936833016853L;

    /**
     * 用户名
     */
    private String name;
}
