package com.ltj.gateway.entity.permission;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ltj.gateway.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * @author: Liu Tian Jun
 * @Date: 2019-11-11 14:09
 * @describe： TODO
 * @version: 1.0
 */
@TableName("permission")
@EqualsAndHashCode(callSuper = false)
@Data
public class Permission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    @TableField("parent_id")
    private Integer parentId;

    private String resources;

    private String title;

    private String icon;

    private String type;

    @TableField("`describe`")
    private String describe;

    @TableField(exist = false)
    private Set<Permission> children;


}
