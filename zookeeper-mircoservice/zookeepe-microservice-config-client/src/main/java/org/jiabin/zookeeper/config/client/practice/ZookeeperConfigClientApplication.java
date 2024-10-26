package org.jiabin.zookeeper.config.client.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperConfigClientApplication.class, args);
    }

}
