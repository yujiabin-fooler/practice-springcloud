package org.jiabin.skywalking.user.service.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SkyUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyUserServiceApplication.class, args);
    }

}
