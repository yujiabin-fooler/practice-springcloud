package org.jiabin.sentinel.dashboard.prctice.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户实体类
 * @author jiabin.yu
 * @Date: 2020/12/2
 */
@Data
public class HelloParam implements Serializable {

    private static final long serialVersionUID = 7668092420785539494L;

    /**
     * 用户名
     */
    private String name;
}
