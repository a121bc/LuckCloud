package com.ltj.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.ltj.gateway.entity.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 登录失败处理
 * @author Liu Tian Jun
 */
@Component
public class AuthenticationFaillHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException authException) {
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        Result result = Result.error401("用户名或密码错误，请重新输入！", authException.getMessage());
        byte[] dataBytes = JSON.toJSONBytes(result);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }
}
