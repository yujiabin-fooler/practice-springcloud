package org.jiabin.sentinel.service.practice.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.jiabin.sentinel.service.practice.domain.CommonResult;

/**
 * @author jiabin.yu
 * @description 自定义限流逻辑处理类
 * @date 2023/12/7
 * 
 */
public class CustomBlockHandler {

    public static CommonResult handleException(BlockException exception){
        return new CommonResult("自定义限流信息",200);
    }
}
