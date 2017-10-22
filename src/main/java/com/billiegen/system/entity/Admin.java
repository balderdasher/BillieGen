package com.billiegen.system.entity;

import com.billiegen.common.framework.entity.BaseEntity;
import com.billiegen.system.enums.Sex;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 管理员
 *
 * @author CodePorter
 * @date 2017-09-30
 */
@Entity
@Where(clause = "del_flag=0")
@Table(name = "sys_admin",
        indexes = @Index(columnList = "username", name = "uk_username", unique = true)
)
public class Admin extends BaseEntity {
    private String username;
    private String password;
    private String nickname;
    private String realname;
    private Sex sex;
    private String email;
    private String salt;
    private Boolean isEnabled;
    private Boolean isLocked;
    private Boolean isExpired;
    private Boolean isCredentialsExpired;
    private Date lockedDate;
    private Date loginDate;
    private String loginIp;
    private Set<Role> roleSet = new HashSet<>();

    @Override
    @Transient
    public void onSave() {
        super.onSave();
        if (this.sex == null) {
            this.sex = Sex.MALE;
        }
        if (this.isEnabled == null) {
            this.isEnabled = true;
        }
        if (this.isLocked == null) {
            this.isLocked = false;
        }
        if (this.isExpired == null) {
            this.isExpired = false;
        }
        if (this.isCredentialsExpired == null) {
            this.isCredentialsExpired = false;
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.onSave();
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_admin_role",
            joinColumns = @JoinColumn(name = "admin_id", foreignKey = @ForeignKey(name = "fk_admin_role_admin")),
            inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_admin_role_role"))
    )
    @OrderBy("sortSeq asc")
    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Column(nullable = false, updatable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(nullable = false)
    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Column(nullable = false)
    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    @Column(nullable = false)
    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    @Column(nullable = false)
    public Boolean getCredentialsExpired() {
        return isCredentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        isCredentialsExpired = credentialsExpired;
    }

    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
