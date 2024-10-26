package org.jiabin.nacos.remote.service.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosRemoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosRemoteServiceApplication.class, args);
    }

}
