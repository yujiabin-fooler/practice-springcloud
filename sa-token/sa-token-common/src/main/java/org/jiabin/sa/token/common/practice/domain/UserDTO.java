package org.jiabin.sa.token.common.practice.domain;

import lombok.*;
import java.util.List;

/**
 * @author jiabin.yu
 * @description 用户信息类
 * @date 2020/6/19
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private List<String> permissionList;
}
