package org.jiabin.seata.storage.service.practice.controller;


import org.jiabin.seata.storage.service.practice.domain.CommonResult;
import org.jiabin.seata.storage.service.practice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiabin.yu
 * @description 库存管理Controller
 * @date 2023/12/8
 * 
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult("扣减库存成功！",200);
    }
}
