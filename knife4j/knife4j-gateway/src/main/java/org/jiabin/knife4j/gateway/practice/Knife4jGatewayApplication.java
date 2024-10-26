package org.jiabin.knife4j.gateway.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Knife4jGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Knife4jGatewayApplication.class, args);
    }

}
