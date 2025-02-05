package org.jiabin.gateway.filter.practice.service;

import org.jiabin.gateway.filter.practice.common.api.ApiResult;
import org.jiabin.gateway.filter.practice.model.*;

/**
 * @Description: 白名单业务接口
 * @author jiabin.yu
 * @Date: 2022/8/23
 */
public interface WhiteListService {


    /**
     * 新增单条
     *
     * @param addParam
     * @return
     */
    ApiResult add(WhiteListAddParam addParam);

    /**
     * 查询单条
     *
     * @param infoParam
     * @return
     */
    ApiResult info(WhiteListInfoParam infoParam);

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    ApiResult page(WhiteListPageParam pageParam);

    /**
     * 更新单条
     *
     * @param updateParam
     * @return
     */
    ApiResult update(WhiteListUpdateParam updateParam);

    /**
     * 删除单条
     *
     * @param deleteParam
     * @return
     */
    ApiResult delete(WhiteListDeleteParam deleteParam);

}
