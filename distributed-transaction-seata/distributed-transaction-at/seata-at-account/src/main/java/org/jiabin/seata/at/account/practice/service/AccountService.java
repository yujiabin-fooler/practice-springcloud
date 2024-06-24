package org.jiabin.seata.at.account.practice.service;

public interface AccountService {

    Integer deduct(Integer userId, Integer amount);
}
