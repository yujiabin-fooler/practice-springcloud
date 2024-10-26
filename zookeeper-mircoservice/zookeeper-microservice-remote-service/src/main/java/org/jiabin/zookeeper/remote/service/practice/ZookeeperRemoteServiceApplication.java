package org.jiabin.zookeeper.remote.service.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperRemoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperRemoteServiceApplication.class, args);
    }

}
