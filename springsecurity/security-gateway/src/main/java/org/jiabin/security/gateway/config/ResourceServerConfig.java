package org.jiabin.security.gateway.config;

import org.jiabin.security.gateway.component.RestAuthenticationEntryPoint;
import org.jiabin.security.gateway.component.RestfulAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author jiabin.yu
 * @description Oauth2资源服务器配置
 * @date 2023/12/21
 * 
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class ResourceServerConfig {

    /**
     * Spring Security默认的SecurityFilterChain配置
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, RestAuthenticationEntryPoint authenticationEntryPoint, RestfulAccessDeniedHandler accessDeniedHandler) {
        //禁用csrf与cors
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        http.cors(ServerHttpSecurity.CorsSpec::disable);
        //开启全局验证
        http.authorizeExchange((authorize) -> authorize.anyExchange().authenticated());
        //开启OAuth2登录
        http.oauth2Login(Customizer.withDefaults());
        //Oauth2资源服务配置，开启JWT的bearer token支持
        http.oauth2ResourceServer((resourceServer) -> resourceServer
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverterAdapter()))
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler));
        return http.build();
    }

    /**
     * JWT授权转换器配置
     */
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverterAdapter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        //设置授权转换器的前缀，默认为SCOPE_
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        //设置在JWT中权限数组对应的key名称
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}


