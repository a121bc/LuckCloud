package com.ltj.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ltj.gateway.entity.gateway_route.GatewayRoute;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {

    /**
     * @Description 查询有效的路由集合
     * @param
     * @return java.util.List<com.ltj.gateway.entity.gateway_route.GatewayRoute>
     * @author Liu Tian Jun
     * @date 08:54 2019-12-23 0023
     **/
    List<GatewayRoute> selectEffectList();
}
