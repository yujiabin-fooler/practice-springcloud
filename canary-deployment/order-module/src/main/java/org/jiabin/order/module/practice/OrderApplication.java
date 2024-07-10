package org.jiabin.order.module.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Kerwin
 * @date 2023/7/14
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.jiabin.*.client")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }
}
