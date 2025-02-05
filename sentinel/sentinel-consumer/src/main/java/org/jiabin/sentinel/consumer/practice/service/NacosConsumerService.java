package org.jiabin.sentinel.consumer.practice;

import org.jiabin.sentinel.consumer.practice.constant.NacosConst;
import org.jiabin.sentinel.consumer.practice.param.HelloParam;
import org.jiabin.sentinel.consumer.practice.service.fallback.ConsumerFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @Description: Nacos 服务消费者业务层
 * @author jiabin.yu
 * @Date: 2020/12/2
 */
@FeignClient(name = NacosConst.CLOUD_ALIBABA_SENTINEL_DASHBOARD, fallbackFactory = ConsumerFallBackFactory.class)
public interface NacosConsumerService {

    /**
     * hello
     * @param helloParam
     * @return
     */
    @GetMapping(value = "/api/cloud/alibaba/sentinel/dashboard/hello", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> hello(@SpringQueryMap HelloParam helloParam);

    /**
     *
     * @param helloParam
     * @return
     */
    @PostMapping(value = "/api/cloud/alibaba/sentinel/dashboard/replay", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> replay(@RequestBody HelloParam helloParam);


}
