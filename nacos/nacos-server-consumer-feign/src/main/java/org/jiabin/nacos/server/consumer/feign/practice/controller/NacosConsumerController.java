package org.jiabin.nacos.server.consumer.feign.practice.controller;

import org.jiabin.nacos.server.consumer.feign.practice.param.HelloParam;
import org.jiabin.nacos.server.consumer.feign.practice.service.NacosConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: nacos 服务消费者控制层
 * @author jiabin.yu
 * @Date: 2020/12/2
 */
@Slf4j
@RestController
@RequestMapping("/api/nacos/consumer")
public class NacosConsumerController {

    @Autowired
    private NacosConsumerService consumerService;

    @GetMapping(value = "/hello", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> hello(HelloParam helloParam) {
        log.info("/hello request data: {}", helloParam);
        ResponseEntity<String> response = consumerService.hello(helloParam);
        log.info("response: {}", response.getBody());
        return response;
    }

    @PostMapping(value = "/replay", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> replay(@RequestBody HelloParam helloParam) {
        log.info("/replay request data: {}", helloParam);
        ResponseEntity<String> response = consumerService.replay(helloParam);
        log.info("response: {}", response.getBody());
        return response;
    }


}
