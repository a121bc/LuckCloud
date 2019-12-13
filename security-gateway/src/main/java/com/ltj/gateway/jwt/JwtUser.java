package com.ltj.gateway.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ltj.gateway.entity.role.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author: Liu Tian Jun
 * @Date: 2019-11-11 11:57
 * @describe： TODO
 * @version: 1.0
 */
@ToString
public class JwtUser implements UserDetails {

    @Getter
    @Setter
    private Integer id;

    @Setter
    private String username;

    @Setter
    private String password;

    @Getter
    @Setter
    private Integer state;

    @Getter
    @Setter
    private Set<Role> roles;

    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles.size()>0) {
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return state == 1;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
