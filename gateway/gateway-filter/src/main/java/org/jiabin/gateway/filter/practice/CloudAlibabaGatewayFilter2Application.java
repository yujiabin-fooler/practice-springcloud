package org.jiabin.gateway.filter.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author junqiang.lu
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.ljq.demo.springboot.*"})
@MapperScan(basePackages = {"org.jiabin.gateway.filter.practice.dao"})
public class CloudAlibabaGatewayFilter2Application {

    public static void main(String[] args) {
        System.setProperty("server.port", "8613");
        SpringApplication.run(CloudAlibabaGatewayFilter2Application.class, args);
    }

}
