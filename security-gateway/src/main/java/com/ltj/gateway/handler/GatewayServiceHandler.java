package com.ltj.gateway.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ltj.gateway.entity.gateway_route.GatewayRoute;
import com.ltj.gateway.mapper.GatewayRouteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

/**
 * @describe： TODO
 * @author: Liu Tian Jun
 * @Date: 2019-12-20 15:37
 * @version: 1.0
 */

@Slf4j
@Service
public class GatewayServiceHandler implements ApplicationEventPublisherAware, CommandLineRunner {

    private static final String HTTP = "http";
    private static final String PATH = "Path";
    private static final String PATTERN = "pattern";
    private static final String STRIP_PREFIX = "StripPrefix";
    private static final String GENKEY_0 = "_genkey_0";


    private ApplicationEventPublisher publisher;

    @Autowired
    private GatewayRouteMapper gatewayRouteMapper;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Override
    public void run(String... args) throws Exception {
        this.loadRouteConfig();
    }

    public void loadRouteConfig() {
        List<GatewayRoute> gatewayRoutes = gatewayRouteMapper.selectEffectList();
        Optional.ofNullable(gatewayRoutes).orElse(Collections.emptyList())
            .forEach(gatewayRoute -> {
                RouteDefinition definition = new RouteDefinition();
                Map<String, String> predicateParams = new HashMap<>(8);
                PredicateDefinition predicate = new PredicateDefinition();
                FilterDefinition filterDefinition = new FilterDefinition();
                Map<String, String> filterParams = new HashMap<>(8);
                URI uri;
                if (gatewayRoute.getUri().startsWith(HTTP)) {
                    // http 地址
                    uri = UriComponentsBuilder.fromHttpUrl(gatewayRoute.getUri()).build().toUri();
                } else {
                    // 注册中心
                    uri = UriComponentsBuilder.fromUriString("lb://"+gatewayRoute.getUri()).build().toUri();
                }
                definition.setId(gatewayRoute.getId().toString());
                // 名称是固定的，spring gateway会根据名称找对应的PredicateFactory
                predicate.setName(PATH);
                predicateParams.put(PATTERN, gatewayRoute.getPredicates());
                predicate.setArgs(predicateParams);

                // 名称是固定的, 路径去前缀
                filterDefinition.setName(STRIP_PREFIX);
                filterParams.put(GENKEY_0, gatewayRoute.getFilters());
                filterDefinition.setArgs(filterParams);

                definition.setPredicates(Arrays.asList(predicate));
                definition.setFilters(Arrays.asList(filterDefinition));
                definition.setUri(uri);

                routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            });
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

}
