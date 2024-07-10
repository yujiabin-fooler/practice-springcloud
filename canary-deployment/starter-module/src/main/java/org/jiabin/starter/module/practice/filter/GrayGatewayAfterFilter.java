package org.jiabin.starter.module.practice.filter;

import org.jiabin.starter.module.practice.holder.GrayFlagRequestHolder;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class GrayGatewayAfterFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 请求执行完必须要remore当前线程的ThreadLocal
        GrayFlagRequestHolder.remove();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 设置过滤器的执行顺序，值越小越先执行
        return Ordered.LOWEST_PRECEDENCE;
    }
}