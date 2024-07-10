package org.jiabin.starter.module.practice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@RefreshScope
@ConfigurationProperties("kerwin.tool.gray.gateway")
public class GrayGatewayProperties {

    /**
     * 灰度开关（如果开启灰度开关则进行灰度逻辑处理，如果关闭则走正常处理逻辑）
     * PS：一般在灰度发布测试完成以后会将线上版本都切换成灰度版本完成全部升级，这时候应该关闭灰度逻辑判断
     */
    private Boolean enabled = true;

    /**
     * 自定义灰度版本请求头 （通过grayHeadValue来匹配请求头中的值如果一致就去调用灰度版本,用于公司测试）
     */
    private String grayHeadKey="gray";

    /**
     * 自定义灰度版本请求头匹配值
     */
    private String grayHeadValue="gray-996";

    /**
     * 使用灰度版本IP数组
     */
    private List<String> grayIPList = new ArrayList<>();

    /**
     * 使用灰度版本城市数组
     */
    private List<String> grayCityList = new ArrayList<>();

    /**
     * 使用灰度版本用户编号数组（我们系统不会在网关获取用户编号这种方法如果需要可以自己实现一下）
     */
    private List<String> grayUserNoList = new ArrayList<>();
}