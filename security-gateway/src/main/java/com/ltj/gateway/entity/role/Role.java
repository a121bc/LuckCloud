package com.ltj.gateway.entity.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ltj.gateway.entity.BaseEntity;
import com.ltj.gateway.entity.permission.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * @author: Liu Tian Jun
 * @Date: 2019-11-11 14:06
 * @describe： 权限
 * @version: 1.0
 */

@TableName("role")
@EqualsAndHashCode(callSuper = false)
@Data
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    private String name;

    @TableField("`describe`")
    private String describe;

    private Integer state;

    @TableField(exist = false)
    private Set<Permission> permissions;
}
