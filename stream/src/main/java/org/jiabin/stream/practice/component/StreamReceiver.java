package org.jiabin.stream.practice.component;

/**
 * Created by jiabin.yu on 2023/12/14.
 */

import cn.hutool.json.JSONUtil;
import org.jiabin.stream.practice.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * @author jiabin.yu
 * @description Stream消息接收者
 * @date 2023/12/14
 * 
 */
@Slf4j
@Configuration
public class StreamReceiver {

    @Bean
    public Consumer<String> consumeMsg() {
        return str -> {
            log.info("consumeMsg方法被调用，收到消息：{}" ,str);
        };
    }

    @Bean
    public Consumer<User> consumeUser() {
        return user -> {
            log.info("consumeUser方法被调用，收到消息：{}" , JSONUtil.toJsonStr(user));
        };
    }

    @Bean
    public Consumer<String> consumeConvertMsg() {
        return str -> {
            log.info("consumeConvertMsg方法被调用，收到消息：{}" , str);
        };
    }
}
