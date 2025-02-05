package org.jiabin.sa.token.gateway.practice.config;

import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author jiabin.yu
 * @description Sa-Token相关配置
 * @date 2023/11/28
 * 
 */
@Configuration
public class SaTokenConfig {
    /**
     * 注册Sa-Token全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 开放地址
                .addExclude("/favicon.ico")
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    // 登录认证：除登录接口都需要认证
                    SaRouter.match("/**", "/auth/user/login", r -> StpUtil.checkLogin());
                    // 权限认证：不同接口访问权限不同
                    SaRouter.match("/api/test/hello", r -> StpUtil.checkPermission("api:test:hello"));
                    SaRouter.match("/api/user/info", r -> StpUtil.checkPermission("api:user:info"));
                })
                // setAuth方法异常处理
                .setError(e -> {
                    //设置错误返回格式为JSON
                    ServerWebExchange exchange = SaReactorSyncHolder.getContext();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    return SaResult.error(e.getMessage());
                });
    }
}
