package org.jiabin.starter.module.practice.handler;

import org.jiabin.starter.module.practice.holder.GrayFlagRequestHolder;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;


public class GrayGatewayExceptionHandler implements WebExceptionHandler, Ordered {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        // 请求执行完必须要remore当前线程的ThreadLocal
        GrayFlagRequestHolder.remove();
        ServerHttpResponse response = exchange.getResponse();
        if (ex instanceof ResponseStatusException) {
            // 处理 ResponseStatusException 异常
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            response.setStatusCode(responseStatusException.getStatus());
            // 可以根据需要设置响应头等
            return response.setComplete();
        } else {
            // 处理其他异常
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            // 可以根据需要设置响应头等
            return response.setComplete();
        }
    }

    @Override
    public int getOrder() {
        // 设置过滤器的执行顺序，值越小越先执行
        return Ordered.HIGHEST_PRECEDENCE;
    }
}