package org.jiabin.eureka.remote.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaRemoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRemoteServiceApplication.class, args);
    }

}
