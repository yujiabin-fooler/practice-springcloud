package com.jiabin.feign.consumer.client.practice.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiabin.yu
 * @description OpenFeign配置类
 * @date 2023/12/6
 * 
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
