package com.ltj.gateway.handler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltj.gateway.entity.AuthUserDetails;
import com.ltj.gateway.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;


@Component
public class AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication){
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");

        Result result;
        try {
            User user=(User)authentication.getPrincipal();
            AuthUserDetails userDetails=buildUser(user);
            byte[] authorization=(userDetails.getUsername()+":"+userDetails.getPassword()).getBytes();
            String token= Base64.getEncoder().encodeToString(authorization);
            httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
            userDetails.setToken(token);
            result = Result.success("登录成功",userDetails);
        }
        catch (Exception ex){
            ex.printStackTrace();
            result = Result.error401("授权异常",ex.getMessage());
        }
        byte[] bytes = JSONObject.toJSONBytes(result);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }



    private AuthUserDetails buildUser(User user){
        AuthUserDetails userDetails=new AuthUserDetails();
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDetails;
    }

}
