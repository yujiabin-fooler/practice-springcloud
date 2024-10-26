package org.jiabin.consul.remote.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulRemoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulRemoteServiceApplication.class, args);
    }

}
