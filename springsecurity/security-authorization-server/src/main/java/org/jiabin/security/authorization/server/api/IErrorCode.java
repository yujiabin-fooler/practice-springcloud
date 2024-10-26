package org.jiabin.security.authorization.server.api;

/**
 * 封装API的错误码
 * Created by jiabin.yu on 2019/4/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
