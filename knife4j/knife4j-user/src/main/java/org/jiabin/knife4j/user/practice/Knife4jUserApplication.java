package org.jiabin.knife4j.user.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Knife4jUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(Knife4jUserApplication.class, args);
    }

}
