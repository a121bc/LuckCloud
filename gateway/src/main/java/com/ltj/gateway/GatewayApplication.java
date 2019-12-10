package com.ltj.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @describe： 网关
 * @author: Liu Tian Jun
 * @Date: 2019-12-10 21:24
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    /**
     * [
     *     {
     *         "enable":true,
     *         "id":"baidu",
     *         "path":"/baidu/**",
     *         "retryable":false,
     *         "stripPrefix":true,
     *         "url":"http://www.baidu.com"
     *     }, {
     *         "enable":true,
     *         "id":"163",
     *         "path":"/163/**",
     *         "retryable":false,
     *         "stripPrefix":true,
     *         "url":"https://www.163.com"
     *     },
     *     {
     *         "enable":true,
     *         "id":"service-provider",
     *         "serviceId":"service-provider",
     *         "path":"/provider/**",
     *         "retryable":false,
     *         "stripPrefix":true
     *     }
     *
     * ]
     */
}
