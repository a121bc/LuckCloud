package com.ltj.gateway.entity.permission;

import lombok.Data;

import java.util.List;

/**
 * @author: Liu Tian Jun
 * @Date: 2019-11-11 14:19
 * @describe： TODO
 * @version: 1.0
 */

@Data
public class Menu {

    private Integer id;

    private Integer parentId;

    private String icon;

    private String resources;

    private String title;

    private List<Menu> children;

    public Menu() {
    }

    public Menu(Integer id, Integer parentId, String icon, String resources, String title) {
        this.id = id;
        this.parentId = parentId;
        this.icon = icon;
        this.resources = resources;
        this.title = title;
    }
}
