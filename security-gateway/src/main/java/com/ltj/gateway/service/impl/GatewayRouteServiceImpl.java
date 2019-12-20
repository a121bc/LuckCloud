package com.ltj.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltj.gateway.entity.gateway_route.GatewayRoute;
import com.ltj.gateway.mapper.GatewayRouteMapper;
import com.ltj.gateway.service.IGatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements IGatewayRouteService {


}
