package org.jiabin.knife4j.user.practice.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

/**
 * @author jiabin.yu
 * @description 用户实体类
 * @date 2023/11/29
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class User {
    @Schema(title = "用户ID")
    private Long id;
    @Schema(title = "用户名")
    private String username;
    @Schema(title = "密码")
    private String password;

    @Tolerate
    public User(){
    }
}
