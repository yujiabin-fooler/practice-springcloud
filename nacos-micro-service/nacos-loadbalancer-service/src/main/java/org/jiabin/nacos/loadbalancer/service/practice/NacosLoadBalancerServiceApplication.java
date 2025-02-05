package org.jiabin.nacos.loadbalancer.service.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class NacosLoadBalancerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosLoadBalancerServiceApplication.class, args);
    }

}
