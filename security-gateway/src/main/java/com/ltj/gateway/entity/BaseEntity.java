package com.ltj.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Liu Tian Jun
 */
@Getter
@Setter
@ToString
public class BaseEntity {

    @TableField("create_time")
    protected Date createTime;

    @TableField("up_time")
    protected Date upTime;

    @TableField(exist = false)
    private Integer page = 1;

    @TableField(exist = false)
    private Integer size = 10;
}
