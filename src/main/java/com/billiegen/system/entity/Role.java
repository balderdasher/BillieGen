package com.billiegen.system.entity;

import com.billiegen.common.framework.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 *
 * @author CodePorter
 * @date 2017-09-30
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity {
    private String name;
    private Boolean isSystem;
    private String description;
    private Set<Right> allRights;
    private Set<Admin> adminSet = new HashSet<>();

    @Column(unique = true,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Boolean getSystem() {
        return isSystem;
    }

    public void setSystem(Boolean system) {
        isSystem = system;
    }

    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany
    public Set<Right> getAllRights() {
        return allRights;
    }

    public void setAllRights(Set<Right> allRights) {
        this.allRights = allRights;
    }

    @ManyToMany
    public Set<Admin> getAdminSet() {
        return adminSet;
    }

    public void setAdminSet(Set<Admin> adminSet) {
        this.adminSet = adminSet;
    }
}
