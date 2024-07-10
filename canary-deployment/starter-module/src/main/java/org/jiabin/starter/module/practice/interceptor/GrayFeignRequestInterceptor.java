package org.jiabin.starter.module.practice.interceptor;

import org.jiabin.starter.module.practice.constant.GrayConstant;
import org.jiabin.starter.module.practice.enums.GrayStatusEnum;
import org.jiabin.starter.module.practice.holder.GrayFlagRequestHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Collections;

public class GrayFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 如果灰度标记存在，将灰度标记通过HttpHeader传递下去
        GrayStatusEnum grayStatusEnum = GrayFlagRequestHolder.getGrayTag();
        if (grayStatusEnum != null ) {
            template.header(GrayConstant.GRAY_HEADER, Collections.singleton(grayStatusEnum.getVal()));
        }
    }
}