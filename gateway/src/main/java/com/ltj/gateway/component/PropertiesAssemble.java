package com.ltj.gateway.component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.ltj.gateway.entity.ZuulRouteEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe： 组装类
 * @author: Liu Tian Jun
 * @Date: 2019-12-10 21:52
 * @version: 1.0
 */
public class PropertiesAssemble {

    @Autowired
    private ConfigService configService;

    @Value("${spring.application.name}")
    private String nacosDataId;
    @Value("${spring.cloud.nacos.config.group}")
    private String nacosGroupId;

    public Map<String, ZuulProperties.ZuulRoute> getProperties() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        List<ZuulRouteEntity> results = listenerNacos(nacosDataId,nacosGroupId);
        for (ZuulRouteEntity result : results) {
            if (StringUtils.isBlank(result.getPath())) {
                continue;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                BeanUtils.copyProperties(result, zuulRoute);
            } catch (Exception e) {
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }




    private List<ZuulRouteEntity> listenerNacos (String dataId, String group) {
        try {
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println("从Nacos返回的配置：" + content);
            return JSONObject.parseArray(content, ZuulRouteEntity.class);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
