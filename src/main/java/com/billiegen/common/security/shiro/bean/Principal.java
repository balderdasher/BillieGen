package com.billiegen.common.security.shiro.bean;

import com.billiegen.system.entity.Admin;
import com.billiegen.system.entity.Menu;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 授权用户信息
 *
 * @author CodePorter
 * @date 2017-10-20
 */
public class Principal implements Serializable {
    private String id;
    private String username;
    private String email;
    private String name;
    private Set<String> roles = new HashSet<>();
    private Set<String> rights = new HashSet<>();
    private Set<Menu> menus = new HashSet<>();

    public Principal(Admin user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getRealname();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getRights() {
        return rights;
    }

    public void setRights(Set<String> rights) {
        this.rights = rights;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
