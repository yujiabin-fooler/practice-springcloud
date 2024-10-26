package org.jiabin.security.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SasGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SasGatewayApplication.class, args);
    }

}
