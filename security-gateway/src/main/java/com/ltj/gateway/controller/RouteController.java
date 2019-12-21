package com.ltj.gateway.controller;

import com.ltj.gateway.common.BaseController;
import com.ltj.gateway.entity.Result;
import com.ltj.gateway.entity.gateway_route.GatewayRoute;
import com.ltj.gateway.handler.GatewayServiceHandler;
import com.ltj.gateway.service.IGatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @describe： 路由
 * @author: Liu Tian Jun
 * @Date: 2019-12-20 16:46
 * @version: 1.0
 */


@RestController
@RequestMapping("/route")
public class RouteController extends BaseController<GatewayRoute, IGatewayRouteService> {

    @Autowired
    private GatewayServiceHandler gatewayServiceHandler;

    @GetMapping("/refresh")
    public Mono<Result> refresh() {
        return Mono.just(Result.success("操作成功")).doOnNext(e ->gatewayServiceHandler.loadRouteConfig());
    }

    @Override
    @PostMapping("/saveOrUpdate")
    public Mono<Result> saveOrUpdate(GatewayRoute entity) {
        return super.saveOrUpdate(entity).doOnNext(e -> gatewayServiceHandler.loadRouteConfig());
    }

    @GetMapping("/delete")
    public Mono<Result> delete(@PathVariable String id) throws Exception {
        return super.delete(id).doOnNext(e -> gatewayServiceHandler.loadRouteConfig());
    }
}
