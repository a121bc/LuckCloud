package com.ltj.gateway.entity.user;

import com.ltj.gateway.entity.permission.Button;
import com.ltj.gateway.entity.permission.Menu;
import com.ltj.gateway.entity.role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Set;

/**
 * @author: Liu Tian Jun
 * @Date: 2019-11-11 14:02
 * @describe： TODO
 * @version: 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {

    private Set<Role> roles;

    /**
     * @describe 按钮
     * @date 2018/10/29
     * @author Wang Chen Chen
     */
    private Set<Button> buttons;

    /**
     * @describe 菜单
     * @date 2018/10/29
     * @author Wang Chen Chen
     */
    private Set<Menu> menus;

    public User toSysUser() {
        User user = new User();
        user.setAvatar(this.avatar);
        user.setUsername(this.username);
        user.setNickname(this.nickname);
        user.setMail(this.mail);
        return user;
    }

    public UserVo(Integer id, String avatar, String nickname, String username, String mail) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.username = username;
        this.mail = mail;
    }

    public UserVo(Integer id, String avatar, String nickname, String username, String mail, Integer gender, Date birthday, Integer state, Set<Role> roles, Date createTime, Date upTime) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.username = username;
        this.mail = mail;
        this.gender = gender;
        this.birthday = birthday;
        this.state = state;
        this.roles = roles;
        this.createTime = createTime;
        this.upTime = upTime;
    }

    public UserVo(User user) {
        this.id = user.id;
        this.avatar = user.avatar;
        this.nickname = user.nickname;
        this.username = user.username;
        this.mail = user.mail;
        this.gender = user.gender;
        this.birthday = user.birthday;
        this.state = user.state;
        this.createTime = user.getCreateTime();
        this.upTime = user.getUpTime();
    }
}
