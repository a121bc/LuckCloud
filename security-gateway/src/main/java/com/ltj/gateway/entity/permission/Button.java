package com.ltj.gateway.entity.permission;

import lombok.Data;

/**
 * @author: Liu Tian Jun
 * @Date: 2019-11-11 14:18
 * @describe： TODO
 * @version: 1.0
 */

@Data
public class Button {

    private Integer id;

    private String resources;

    private String title;

    public Button() {
    }

    public Button(Integer id, String resources, String title) {
        this.id = id;
        this.resources = resources;
        this.title = title;
    }
}
