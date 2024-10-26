package org.jiabin.seata.order.service.practice.service;

import org.jiabin.seata.order.service.practice.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jiabin.yu 
 * @description 远程调用storage-service的接口
 * @date 2023/12/8
 * 
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    /**
     * 扣减库存
     */
    @GetMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
