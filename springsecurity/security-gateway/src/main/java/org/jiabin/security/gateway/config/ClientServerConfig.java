package org.jiabin.security.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jiabin.yu
 * @description Oauth2客户端配置
 * @date 2023/12/21
 * 
 */
@Configuration
public class ClientServerConfig {

    /**
     * 用户权限解析配置，从认证用户对象中获取权限信息以便注入到认证框架中
     */
    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            authorities.forEach(authority -> {
//                if (authority instanceof OAuth2UserAuthority oAuth2UserAuthority) { //JDK17 以上
                if (authority instanceof OAuth2UserAuthority) {
                    OAuth2UserAuthority oAuth2UserAuthority = (OAuth2UserAuthority) authority;
                    //从OAuth2User的权限属性中获取权限
                    Object userAuthorities = oAuth2UserAuthority.getAttributes().get("authorities");
//                    if (userAuthorities instanceof Collection<?> collection) { //JDK17 以上
                    if (userAuthorities instanceof Collection<?>) {
                        Collection<?> collection = (Collection<?>) userAuthorities;
                        //将String类型的权限值转换为SimpleGrantedAuthority对象并添加到mappedAuthorities中
                        collection.stream().filter(a -> a instanceof String)
                                .map(String::valueOf)
                                .map(SimpleGrantedAuthority::new)
                                .forEach(mappedAuthorities::add);
                    }
                }
            });
            return mappedAuthorities;
        };
    }
}


