package com.ltj.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ltj.gateway.entity.user.JwtUser;
import com.ltj.gateway.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {

    /**
     * @Description 查询用户
     * @param username
     * @return com.ltj.gateway.entity.user.JwtUser
     * @author Liu Tian Jun
     * @date 16:15 2019-12-19 0019
     **/
    JwtUser findByUsername(@Param("username") String username);
}
