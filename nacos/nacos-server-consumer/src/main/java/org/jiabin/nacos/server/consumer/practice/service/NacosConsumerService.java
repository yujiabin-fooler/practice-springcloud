package org.jiabin.nacos.server.consumer.practice.service;

import org.jiabin.nacos.server.consumer.practice.param.HelloParam;

/**
 * @Description: Nacos 服务消费者业务层
 * @author jiabin.yu
 * @Date: 2020/12/2
 */
public interface NacosConsumerService {

    /**
     * hello
     *
     * @param helloParam
     * @return
     */
    String hello(HelloParam helloParam);

    /**
     * 回复
     *
     * @param helloParam
     * @return
     */
    String replay(HelloParam helloParam);
}
