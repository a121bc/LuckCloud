package com.ltj.gateway.handler;

import com.alibaba.fastjson.JSONObject;
import com.ltj.gateway.jwt.JWTTokenService;
import com.ltj.gateway.response.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 登陆成功处理
 * @author Liu Tian Jun
 */
@Component
public class AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication){
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = response.getHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        String token = getHttpAuthHeaderValue(authentication);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        Result result = Result.success("登录成功");
        byte[] bytes = JSONObject.toJSONBytes(result);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    private static String getHttpAuthHeaderValue(Authentication authentication){
        return String.join(" ","Bearer",tokenFromAuthentication(authentication));
    }

    private static String tokenFromAuthentication(Authentication authentication){
        return JWTTokenService.generateToken(
                authentication.getName(),
                authentication.getCredentials(),
                authentication.getAuthorities());
    }

}
