package org.jiabin.knife4j.order.practice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiabin.yu
 * @description SpringDoc API文档相关配置
 * @date 2023/11/29
 * 
 */
@Configuration
public class SpringDocConfig {
    private static final String SECURITY_SCHEME_NAME = "BearerAuth";
    @Bean
    public OpenAPI mallTinyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("micro-knife4j-order API")
                        .description("micro-knife4j-order API 演示")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/macrozheng/mall-learning")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot实战电商项目mall（60K+Star）全套文档")
                        .url("http://www.macrozheng.com"))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}

