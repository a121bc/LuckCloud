package com.ltj.gateway.jwt;

import com.ltj.gateway.service.IRedisService;
import com.ltj.security.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Liu Tian Jun
 * @Date: 2019-11-11 13:24
 * @describe： jwt核心拦截器
 * @version: 1.0
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter implements WebFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Lazy
    private IRedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 获取 Request 中的请求头为 “ Authorization ” 的 token 值

        String authHeader = request.getHeaders().getFirst(jwtTokenUtil.getTokenHeader());
        // 验证 值是否以"Bearer "开头
        if (authHeader != null && authHeader.startsWith(jwtTokenUtil.getTokenHead())) {
            // 截取token中"Bearer "后面的值，
            String authToken = authHeader.substring(jwtTokenUtil.getTokenHead().length());
            // 获取用户账号
            String account = jwtTokenUtil.getUsernameFromToken(authToken);
            log.info("JwtAuthenticationTokenFilter[doFilterInternal] checking authentication {} ", account);
            // 验证用户账号是否合法
            if (account != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
                // 根据account去数据库中查询user数据，足够信任token的情况下，可以省略这一步
                JwtUser userDetails = (JwtUser) redisService.getMapField(jwtTokenUtil.getTokenHeader(), account);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user {} setting security context", account);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        return chain.filter(exchange);
    }

    /*@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException {
        // 获取 Request 中的请求头为 “ Authorization ” 的 token 值
        String authHeader = request.getHeader(jwtTokenUtil.getTokenHeader());
        // 验证 值是否以"Bearer "开头
        if (authHeader != null && authHeader.startsWith(jwtTokenUtil.getTokenHead())) {
            // 截取token中"Bearer "后面的值，
            String authToken = authHeader.substring(jwtTokenUtil.getTokenHead().length());
            // 获取用户账号
            String account = jwtTokenUtil.getUsernameFromToken(authToken);
            log.info("JwtAuthenticationTokenFilter[doFilterInternal] checking authentication {} ", account);
            // 验证用户账号是否合法
            if (account != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
                // 根据account去数据库中查询user数据，足够信任token的情况下，可以省略这一步
                JwtUser userDetails = (JwtUser) redisService.getMapField(jwtTokenUtil.getTokenHeader(), account);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user {} setting security context", account);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }*/
}
