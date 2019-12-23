package com.ltj.gateway.config;

import com.ltj.gateway.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author Liu Tian Jun
 */
@Component
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        UserDetails user = userMapper.findByUsername(username);
        return Mono.justOrEmpty(user)
                .onErrorResume(e -> Mono.error(new UsernameNotFoundException("User Not Found")));
    }



}
