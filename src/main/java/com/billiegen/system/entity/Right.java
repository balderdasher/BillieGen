package com.billiegen.system.entity;

import com.billiegen.common.framework.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * 权限
 *
 * @author CodePorter
 * @date 2017-09-30
 */
@Entity
@Table(name = "sys_role")
public class Right extends BaseEntity {
    private String rightName;
    private String rightCode;
    private String rightRemark;
    private Set<Role> roles;
    private Menu menuInfo;
    private String rightLink;

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getRightCode() {
        return rightCode;
    }

    public void setRightCode(String rightCode) {
        this.rightCode = rightCode;
    }

    public String getRightRemark() {
        return rightRemark;
    }

    public void setRightRemark(String rightRemark) {
        this.rightRemark = rightRemark;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Menu getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(Menu menuInfo) {
        this.menuInfo = menuInfo;
    }

    public String getRightLink() {
        return rightLink;
    }

    public void setRightLink(String rightLink) {
        this.rightLink = rightLink;
    }
}
