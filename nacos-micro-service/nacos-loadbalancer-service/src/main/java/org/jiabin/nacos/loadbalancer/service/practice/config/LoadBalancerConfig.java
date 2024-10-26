package org.jiabin.nacos.loadbalancer.service.practice.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;

/**
 * @author jiabin.yu
 * @description LoadBalancer相关配置
 * @date 2023/11/30
 * 
 */
@Configuration
public class LoadBalancerConfig {
    @Bean
    public LoadBalancerRequestTransformer transformer() {
        return new LoadBalancerRequestTransformer() {
            @Override
            public HttpRequest transformRequest(HttpRequest request, ServiceInstance instance) {
                return new HttpRequestWrapper(request) {
                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.putAll(super.getHeaders());
                        headers.add("X-ServiceId", instance.getServiceId());
                        return headers;
                    }
                };
            }
        };
    }
}
