package org.jiabin.user.client.practice;

import org.jiabin.common.practice.api.ApiResult;
import org.jiabin.common.practice.constants.ServiceNames;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author jiabin.yu
 * @CreateTime: 2023-07-15  09:01
 */
@FeignClient(value = ServiceNames.USER_APP,contextId = "userClient")
public interface UserClient {
    @GetMapping("/user/{userNo}")
    ApiResult getUserName(@PathVariable("userNo") String userNo);
}
