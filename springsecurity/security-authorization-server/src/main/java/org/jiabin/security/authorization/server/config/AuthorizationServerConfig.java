package org.jiabin.security.authorization.server.config;

import org.jiabin.security.authorization.server.component.RestAuthenticationEntryPoint;
import org.jiabin.security.authorization.server.component.RestfulAccessDeniedHandler;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jiabin.yu
 * @description 认证中心配置
 * EnableWebSecurity：启用安全功能
 * EnableMethodSecurity：启用基于方法的安全功能
 * jsr250Enabled：启用JSR250注解支持
 * securedEnabled：启用Secured注解支持
 * @date 2023/12/21
 * 
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class AuthorizationServerConfig {

    /**
     * 自定义授权页路径
     */
    private static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";

    /**
     * OAuth2AuthorizationServer的SecurityFilterChain配置
     */
    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        //启用默认的OAuth2AuthorizationServer配置，会忽略认证端点的csrf校验
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults())//开启OpenID Connect 1.0相关支持
                .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI));//设置自定义授权页路径
        http.exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )//当未登录时访问需要登录的页面重定向至登录页
                //OAuth2资源服务器配置，这里配置了JWT支持
                .oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()));
        return http.build();
    }

    /**
     * Spring Security默认的SecurityFilterChain配置
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RestfulAccessDeniedHandler accessDeniedHandler, RestAuthenticationEntryPoint authenticationEntryPoint) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/assets/**", "/webjars/**", "/login")
                .permitAll()//配置白名单路径
                .anyRequest()
                .authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/login"));//指定登录页路径
        //Oauth2资源服务器配置，开启JWT的bearer token支持
        http.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults())
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler));
        return http.build();
    }

    /**
     * 配置密码编码器，Spring Security默认使用BCrypt方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Oauth2注册客户端仓库配置
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("messaging-client")//客户端ID
                .clientSecret(passwordEncoder.encode("123456"))//客户端秘钥
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)//客户端认证方式，基于Basic Auth请求头
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)//配置认证授权模式为授权码模式
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)//配置认证授权模式支持刷新token
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)//配置认证授权模式为客户端模式
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofMinutes(60)).refreshTokenTimeToLive(Duration.ofHours(24)).build())//设置access_token和refresh_token的有效时间
                .redirectUri("http://127.0.0.1:9401/login/oauth2/code/messaging-client-oidc")//配置授权码模式回调地址
                .redirectUri("https://www.baidu.com")
                .scope(OidcScopes.OPENID)//配置客户端授权范围，使用OIDC时必须配置
                .scope(OidcScopes.PROFILE)//配置客户端授权范围，获取用户信息是必须配置
                .scope("message.read")//自定义的授权范围
                .scope("message.write")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())//客户端配置，用户需要确认授权
                .build();
        //创建一个基于JDBC的注册客户端仓库
        JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
        //当客户端不存在时持久化到数据库
        RegisteredClient repositoryByClientId = registeredClientRepository.findByClientId(registeredClient.getClientId());
        if (repositoryByClientId == null) {
            registeredClientRepository.save(registeredClient);
        }
        return registeredClientRepository;
    }

    /**
     * Oauth2授权服务配置
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        //通过上面的RegisteredClientRepository来创建
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * Oauth2授权确认服务配置
     */
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        //通过上面的RegisteredClientRepository来创建
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * JWK源配置，使用非对称加密
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * 生成RSA密钥对，提供给JWK
     */
    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    /**
     * JWT解析器配置
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     * 认证服务器配置
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("http://192.168.3.48:9401")//设置JWT Token的签发地址，注意这里需要改为本机的IP地址
                .build();
    }

    /**
     * UserDetailsService配置，用于获取用户信息（基于内存）
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("admin", "normal")
                .authorities("delete", "update")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * JWT Token自定义配置，将自定义权限添加到Token中
     */
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> oAuth2TokenCustomizer() {
        return context -> {
            //检查登录用户信息是否为UserDetails类型
//            if (context.getPrincipal().getPrincipal() instanceof UserDetails user) { //JDK17及以上
            if (context.getPrincipal().getPrincipal() instanceof UserDetails) {
                UserDetails user = (UserDetails) context.getPrincipal().getPrincipal();
                //获取申请的scopes
                Set<String> scopes = context.getAuthorizedScopes();
                //获取自定义的用户的权限
                Collection<? extends GrantedAuthority> userAuthorities = user.getAuthorities();
                //将自定义的用户权限值转换为Set<String>
                Set<String> authoritySet = Optional.ofNullable(userAuthorities)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet());
                //添加授权的scopes
                authoritySet.addAll(scopes);
                JwtClaimsSet.Builder claims = context.getClaims();
                //将权限信息放入JWT的claims中
                claims.claim("authorities", authoritySet);
            }
        };
    }

    /**
     * JWT授权转换器配置
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // 设置授权转换器的前缀，默认为SCOPE_
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        // 设置在JWT中权限数组对应的key名称
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

}

