package org.jiabin.sentinel.consumer.practice.service.fallback;

import feign.hystrix.FallbackFactory;
import org.jiabin.sentinel.consumer.practice.fallback.ConsumerFallBack;
import org.springframework.stereotype.Component;

/**
 * @Description: Nacos 熔断工厂
 * @Author: junqiang.lu
 * @Date: 2020/12/3
 */
@Component
public class ConsumerFallBackFactory implements FallbackFactory<ConsumerFallBack> {

    @Override
    public ConsumerFallBack create(Throwable cause) {
        return new ConsumerFallBack(cause);
    }
}
