package org.jiabin.starter.module.practice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kerwin
 * @date 2023/7/17
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties("kerwin.tool.gray.version")
public class GrayVersionProperties {
    /**
     * 当前线上版本号
     */
    private String prodVersion;

    /**
     * 灰度版本号
     */
    private String grayVersion;
}
