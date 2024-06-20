package org.jiabin.nacos.server.provider.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author junqiang.lu
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudAlibabaServerProviderApplication2 {

    public static void main(String[] args) {
        System.setProperty("server.port", "8601");
        SpringApplication.run(CloudAlibabaServerProviderApplication2.class, args);
    }

}
