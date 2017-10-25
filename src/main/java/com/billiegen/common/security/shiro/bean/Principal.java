package com.billiegen.common.security.shiro.bean;

import com.billiegen.system.entity.Admin;
import com.billiegen.system.entity.Role;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<String> roleList;

    public Principal(Admin user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getRealname();
        this.roleList = getRoleIdList(user);
    }

    private List<String> getRoleIdList(Admin user) {
        return user.getRoleSet().stream()
                .sorted(Comparator.comparingInt(Role::getSortSeq).reversed())
                .map(Role::getId)
                .collect(Collectors.toList());
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

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
}
