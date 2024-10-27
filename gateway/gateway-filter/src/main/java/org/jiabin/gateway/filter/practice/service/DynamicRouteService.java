package org.jiabin.gateway.filter.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jiabin.gateway.filter.practice.model.RouteDeleteParam;
import org.jiabin.gateway.filter.practice.model.RouteEntity;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * @Description: 动态路由业务接口
 * @author jiabin.yu
 * @Date: 2021/10/19
 */
public interface DynamicRouteService extends IService<RouteEntity> {


    /**
     * 查询启用的路由列表
     *
     * @return
     */
    List<RouteDefinition> listActive();

    /**
     * 新增路由
     *
     * @param routeDefinition
     */
    void add(RouteDefinition routeDefinition);

    /**
     * 修改路由
     *
     * @param routeDefinition
     */
    void update(RouteDefinition routeDefinition);

    /**
     * 删除路由
     *
     * @param routeDeleteParam
     */
    void delete(RouteDeleteParam routeDeleteParam);




}
