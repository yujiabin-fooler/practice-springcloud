package org.jiabin.sa.token.gateway.practice.component;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import org.jiabin.sa.token.common.practice.domain.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiabin.yu
 * @description 自定义权限验证接口扩展
 * @date 2023/11/28
 * 
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限码列表
        UserDTO userDTO = (UserDTO) StpUtil.getSession().get("userInfo");
        return userDTO.getPermissionList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色码列表
        return null;
    }

}

