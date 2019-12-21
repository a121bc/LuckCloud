package com.ltj.gateway.entity.gateway_route;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ltj.gateway.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("gateway_route")
@EqualsAndHashCode(callSuper = false)
public class GatewayRoute extends BaseEntity {

    @TableId(type= IdType.AUTO)
    private Integer id;

    /*  */
    @TableField("service_id")
    private String serviceId;

    /* 状态 0-停用 1-启用 */
    private Integer state;

    /* 转发地址 */
    private String uri;

    /* 访问路径 */
    private String predicates;

    /* 过滤 */
    private String filters;

    /* 顺序 */
    @TableField("`order`")
    private String order;

    /* 创建人 */
    @TableField("creator_id")
    private Integer creatorId;

    /* 更新人 */
    @TableField("updater_id")
    private Integer updaterId;

    /* 备注信息 */
    private String remarks;

}
