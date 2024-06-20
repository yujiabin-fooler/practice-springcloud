package org.jiabin.zookeeper.feign.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author junqiang.lu
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CloudZookeeperFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZookeeperFeignApplication.class, args);
    }

}
