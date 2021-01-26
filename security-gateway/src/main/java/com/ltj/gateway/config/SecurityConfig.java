package com.ltj.gateway.config;

import com.ltj.gateway.bearer.BearerTokenReactiveAuthenticationManager;
import com.ltj.gateway.bearer.ServerHttpBearerAuthenticationConverter;
import com.ltj.gateway.handler.AuthenticationFaillHandler;
import com.ltj.gateway.handler.CustomHttpBasicServerAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {


    @Autowired
    private ServerAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFaillHandler authenticationFaillHandler;
    @Autowired
    private CustomHttpBasicServerAuthenticationEntryPoint customHttpBasicServerAuthenticationEntryPoint;
    @Autowired
    private ReactiveUserDetailsService userDetailsServiceImpl;
    @Autowired
    private ServerHttpBearerAuthenticationConverter bearerConverter;

    @Autowired
    private ReactiveAuthenticationManager authManager;


    //security的鉴权排除列表
    private static final String[] AUTH_WHITELIST = {
//            "/auth/login",
//            "/auth/logout",
            "/api/**",
            "/health",
            "/api/socket/**",
            "/actuator/**"
    };

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
                    // 白名单
                    .pathMatchers(AUTH_WHITELIST).permitAll()
                    .pathMatchers(HttpMethod.OPTIONS).permitAll()
                    .anyExchange().authenticated()
                .and()
                    // httpBasic 验证
//                    .httpBasic()
//                .and()
                    // 表单登录 验证
                    .addFilterAt(basicAuthenticationFilter(), SecurityWebFiltersOrder.HTTP_BASIC)
//                    .formLogin()
//                    .loginPage("/auth/login")
//                .and()
                    .addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                    .exceptionHandling()
                    // 基于http的接口请求鉴权失败
                    .authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint)
                .and()
                    //无状态服务
                    .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                    //必须支持跨域
                    .csrf().disable()
                    .logout().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return  NoOpPasswordEncoder.getInstance(); //默认不加密
    }

    private AuthenticationWebFilter basicAuthenticationFilter(){
        UserDetailsRepositoryReactiveAuthenticationManager authManager
                = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsServiceImpl);
        authManager.setPasswordEncoder(passwordEncoder());
        AuthenticationWebFilter basicAuthenticationFilter = new AuthenticationWebFilter(authManager);
        basicAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        basicAuthenticationFilter.setAuthenticationFailureHandler(authenticationFaillHandler);
        return basicAuthenticationFilter;

    }

    private AuthenticationWebFilter bearerAuthenticationFilter(){
        AuthenticationWebFilter bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
        bearerAuthenticationFilter.setServerAuthenticationConverter(bearerConverter);
        bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));
        return bearerAuthenticationFilter;
    }


}
