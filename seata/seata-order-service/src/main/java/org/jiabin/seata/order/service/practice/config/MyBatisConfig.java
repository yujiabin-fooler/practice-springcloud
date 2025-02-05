package org.jiabin.seata.order.service.practice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiabin.yu 
 * @description MyBatis相关配置
 * @date 2023/12/8
 * 
 */
@Configuration
@MapperScan({"org.jiabin.seata.order.service.practice.dao"})
public class MyBatisConfig {
}
