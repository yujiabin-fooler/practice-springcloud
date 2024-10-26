package org.jiabin.consul.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulUserServiceApplication.class, args);
    }

}
