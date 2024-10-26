package org.jiabin.zookeeper.user.service.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperUserServiceApplication.class, args);
    }

}
