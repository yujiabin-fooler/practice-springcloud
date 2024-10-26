package org.jiabin.zookeeper.user.service.practice.service;

import org.jiabin.zookeeper.user.service.practice.domain.User;

import java.util.List;

/**
 * @author jiabin.yu
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
