package org.jiabin.security.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SasResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SasResourceServerApplication.class, args);
    }

}
