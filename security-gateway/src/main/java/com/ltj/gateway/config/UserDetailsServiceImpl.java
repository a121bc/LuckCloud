package com.ltj.gateway.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Liu Tian Jun
 */
@Component
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Value("${spring.security.user.name}")
    private String userName;

    @Value("${spring.security.user.password}")
    private String password;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        //todo 预留调用数据库根据用户名获取用户
        if(StringUtils.equals(userName,username)){
            UserDetails user = User.withUsername(userName)
                  .password(passwordEncoder.encode(password))
                    .roles("admin").authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"))
                    .build();
            return Mono.just(user);
        }
        else{
            return Mono.error(new UsernameNotFoundException("User Not Found"));

        }

    }



}
