package com.ltj.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe： TODO
 * @author: Liu Tian Jun
 * @Date: 2019-12-13 13:13
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SecurityGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityGatewayApplication.class, args);
    }

    @RequestMapping("/api")
    @RestController
    public class ApiTest {

        @GetMapping("/demo")
        private String  demo(){
            return "demo";
        }
    }
}
