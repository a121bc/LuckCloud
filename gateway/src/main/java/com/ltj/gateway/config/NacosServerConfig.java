package com.ltj.gateway.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @describe： 配置类-nacos配置服务器
 * @author: Liu Tian Jun
 * @Date: 2019-12-10 21:36
 * @version: 1.0
 */
public class NacosServerConfig {

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;
    @Value("${spring.application.name}")
    private String nacosDataId;
    @Value("${spring.cloud.nacos.config.group}")
    private String nacosGroupId;

    @Autowired
    private NewZuulRouteLocator routeLocator;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    public ConfigService configService() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            configService.addListener(nacosDataId, nacosGroupId, new Listener() {
                @Override
                public Executor getExecutor() {
                    //可以发送监听消息到某个MQ
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("Nacos更新了！");
                    //切忌！！！不需要自己去刷新
                    RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
                    publisher.publishEvent(routesRefreshedEvent);
                }
            });

            return configService;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
