package org.jiabin.security.authorization.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SasAuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SasAuthorizationServerApplication.class, args);
    }

}
