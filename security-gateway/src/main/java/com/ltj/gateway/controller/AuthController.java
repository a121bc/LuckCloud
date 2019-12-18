package com.ltj.gateway.controller;

import com.ltj.gateway.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @describe： 权限
 * @author: Liu Tian Jun
 * @Date: 2019-12-18 13:49
 * @version: 1.0
 */

@Slf4j
@RestController
public class AuthController {

    @PostMapping("/auth/login")
    public Flux<Result> login() {
        return Flux.just(Result.success("登录成功"));
    }

}
