package org.jiabin.seata.at.business.practice.feign;

import org.jiabin.seata.at.business.practice.feign.param.OrderCreateParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "order-server")
public interface OrderFeignClient {

    @PostMapping("/order/inter/createOrder")
    Integer createOrder(@RequestBody OrderCreateParam orderCreateParam);
}
