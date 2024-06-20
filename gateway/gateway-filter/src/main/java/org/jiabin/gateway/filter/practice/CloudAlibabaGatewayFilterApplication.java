package org.jiabin.gateway.filter.practice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author junqiang.lu
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.ljq.demo.springboot.*"})
@MapperScan(basePackages = {"org.jiabin.gateway.filter.practice.dao"})
public class CloudAlibabaGatewayFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaGatewayFilterApplication.class, args);
        log.info("--------------[网关拦截器服务启动成功]------------------");
    }

}
