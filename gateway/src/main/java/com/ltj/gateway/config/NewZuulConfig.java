package com.ltj.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe： 配置路由和监听器
 * @author: Liu Tian Jun
 * @Date: 2019-12-10 21:38
 * @version: 1.0
 */
@Configuration
public class NewZuulConfig {

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private ServerProperties serverProperties;

    @Bean
    public NewZuulRouteLocator routeLocator() {
        NewZuulRouteLocator routeLocator = new NewZuulRouteLocator(
                this.serverProperties.getServlet().getContextPath(), this.zuulProperties);
        return routeLocator;
    }

    @Bean
    public ApplicationListener<ApplicationEvent> zuulRefreshRoutesListener() {
        return new ZuulRefreshListener();
    }
}
