package com.ltj.gateway.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ltj.gateway.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Liu Tian Jun
 * @Date: 2019-11-11 13:35
 * @describe： 用户
 * @version: 1.0
 */
@TableName("user")
@EqualsAndHashCode(callSuper = false)
@Data
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    protected Integer id;

    protected String avatar;

    protected String username;

    protected String mail;

    @TableField("open_id")
    protected String openId;

    protected String nickname;

    protected String password;

    protected Integer gender;

    protected Date birthday;

    protected Integer state;

}
