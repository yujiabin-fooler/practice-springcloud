package org.jiabin.admin.client.service;

import org.jiabin.admin.client.domain.User;

import java.util.List;

/**
 * @auther jiabin.yu
 * @description 用户管理Service
 * @date 2023/11/30
 * 
 */
public interface UserService {
    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    User getByUsername(String username);

    List<User> getUserByIds(List<Long> ids);
}
