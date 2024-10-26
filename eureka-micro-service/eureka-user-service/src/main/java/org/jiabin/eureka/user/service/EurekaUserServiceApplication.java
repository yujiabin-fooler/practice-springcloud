package org.jiabin.eureka.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaUserServiceApplication.class, args);
    }

}
