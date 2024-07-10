package org.jiabin.starter.module.practice.filter;

import org.jiabin.starter.module.practice.constant.GrayConstant;
import org.jiabin.starter.module.practice.enums.GrayStatusEnum;
import org.jiabin.starter.module.practice.holder.GrayFlagRequestHolder;
import org.jiabin.starter.module.practice.properties.GrayGatewayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;


public class GrayGatewayBeginFilter implements GlobalFilter, Ordered {
    @Autowired
    private GrayGatewayProperties grayGatewayProperties;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        GrayStatusEnum grayStatusEnum = GrayStatusEnum.ALL;
        // 当灰度开关打开时才进行请求头判断
        if (grayGatewayProperties.getEnabled()) {
            grayStatusEnum = GrayStatusEnum.PROD;
            // 判断是否需要调用灰度版本
            if (checkGray(exchange.getRequest())) {
                grayStatusEnum = GrayStatusEnum.GRAY;
            }
        }
        GrayFlagRequestHolder.setGrayTag(grayStatusEnum);
        ServerHttpRequest newRequest = exchange.getRequest().mutate()
                .header(GrayConstant.GRAY_HEADER, grayStatusEnum.getVal())
                .build();
        ServerWebExchange newExchange = exchange.mutate()
                .request(newRequest)
                .build();
        return chain.filter(newExchange);
    }

    /**
     * 校验是否使用灰度版本
     */
    private boolean checkGray(ServerHttpRequest request) {
        if (checkGrayHeadKey(request) || checkGrayIPList(request) || checkGrayCiryList(request) || checkGrayUserNoList(request)) {
            return true;
        }
        return false;
    }

    /**
     * 校验自定义灰度版本请求头判断是否需要调用灰度版本
     */
    private boolean checkGrayHeadKey(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        if (headers.containsKey(grayGatewayProperties.getGrayHeadKey())) {
            List<String> grayValues = headers.get(grayGatewayProperties.getGrayHeadKey());
            if (!Objects.isNull(grayValues)
                    && grayValues.size() > 0
                    && grayGatewayProperties.getGrayHeadValue().equals(grayValues.get(0))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验自定义灰度版本IP数组判断是否需要调用灰度版本
     */
    private boolean checkGrayIPList(ServerHttpRequest request) {
        List<String> grayIPList = grayGatewayProperties.getGrayIPList();
        if (CollectionUtils.isEmpty(grayIPList)) {
            return false;
        }
        String realIP = request.getHeaders().getFirst("X-Real-IP");
        if (realIP == null || realIP.isEmpty()) {
            realIP = request.getRemoteAddress().getAddress().getHostAddress();
        }
        if (realIP != null && CollectionUtils.contains(grayIPList.iterator(), realIP)) {
            return true;
        }
        return false;
    }

    /**
     * 校验自定义灰度版本城市数组判断是否需要调用灰度版本
     */
    private boolean checkGrayCiryList(ServerHttpRequest request) {
        List<String> grayCityList = grayGatewayProperties.getGrayCityList();
        if (CollectionUtils.isEmpty(grayCityList)) {
            return false;
        }
        String realIP = request.getHeaders().getFirst("X-Real-IP");
        if (realIP == null || realIP.isEmpty()) {
            realIP = request.getRemoteAddress().getAddress().getHostAddress();
        }
        // 通过IP获取当前城市名称
        // 这里篇幅比较长不具体实现了,想要实现的可以使用ip2region.xdb,这里写死cityName = "本地"
        String cityName = "本地";
        if (cityName != null && CollectionUtils.contains(grayCityList.iterator(), cityName)) {
            return true;
        }
        return false;
    }

    /**
     * 校验自定义灰度版本用户编号数组（我们系统不会在网关获取用户编号这种方法如果需要可以自己实现一下）
     */
    private boolean checkGrayUserNoList(ServerHttpRequest request) {
        List<String> grayUserNoList = grayGatewayProperties.getGrayUserNoList();
        if (CollectionUtils.isEmpty(grayUserNoList)) {
            return false;
        }
        return false;
    }

    @Override
    public int getOrder() {
        // 设置过滤器的执行顺序，值越小越先执行
        return Ordered.HIGHEST_PRECEDENCE;
    }
}