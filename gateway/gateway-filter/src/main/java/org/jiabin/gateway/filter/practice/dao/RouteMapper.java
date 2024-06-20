package org.jiabin.gateway.filter.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jiabin.gateway.filter.practice.model.RouteEntity;
import org.springframework.stereotype.Repository;

/**
 * @Description: 路由数据库持久层
 * @Author: junqiang.lu
 * @Date: 2021/10/21
 */
@Repository
public interface RouteMapper extends BaseMapper<RouteEntity> {

}
