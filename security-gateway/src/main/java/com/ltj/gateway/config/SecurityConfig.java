package com.ltj.gateway.config;

import com.ltj.gateway.handler.AuthenticationFaillHandler;
import com.ltj.gateway.handler.AuthenticationSuccessHandler;
import com.ltj.gateway.handler.CustomHttpBasicServerAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {


    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFaillHandler authenticationFaillHandler;
    @Autowired
    private CustomHttpBasicServerAuthenticationEntryPoint customHttpBasicServerAuthenticationEntryPoint;


    //security的鉴权排除列表
    private static final String[] EXCLUDED_AUTH_PAGES = {
            "/auth/login",
            "/auth/logout",
            "/health",
            "/api/socket/**"
    };

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
                .pathMatchers(EXCLUDED_AUTH_PAGES).permitAll()  //无需进行权限过滤的请求路径
                .pathMatchers(HttpMethod.OPTIONS).permitAll() //option 请求默认放行
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().loginPage("/auth/login")
                .authenticationSuccessHandler(authenticationSuccessHandler) //认证成功
                .authenticationFailureHandler(authenticationFaillHandler) //登陆验证失败
                .and().exceptionHandling().authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint)  //基于http的接口请求鉴权失败
                .and() .csrf().disable()//必须支持跨域
                .logout().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //默认不加密
    }


}
