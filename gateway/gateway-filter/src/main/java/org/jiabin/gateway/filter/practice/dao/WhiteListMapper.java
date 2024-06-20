package org.jiabin.gateway.filter.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jiabin.gateway.filter.practice.model.WhiteListEntity;
import org.springframework.stereotype.Repository;

/**
 * @Description: 网关路由白名单DAO接口
 * @Author: junqiang.lu
 * @Date: 2022/8/23
 */
@Repository
public interface WhiteListMapper extends BaseMapper<WhiteListEntity> {


}
