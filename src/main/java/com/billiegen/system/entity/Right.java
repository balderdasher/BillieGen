package com.billiegen.system.entity;

import com.billiegen.common.framework.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * 权限
 *
 * @author CodePorter
 * @date 2017-09-30
 */
@Entity
@Table(name = "sys_right",
        indexes = {
                @Index(columnList = "rightCode", name = "uk_right_code", unique = true),
                @Index(columnList = "menu_code", name = "idx_menu_code")
        }
)
public class Right extends BaseEntity {
    private String rightName;
    private String rightCode;
    private String permission;
    private String rightRemark;
    private Set<Role> roleSet;
    private Menu menuInfo;
    private String rightLink;

    @Column(nullable = false, length = 32)
    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    @Column(nullable = false, updatable = false, length = 4)
    public String getRightCode() {
        return rightCode;
    }

    @Column(length = 64)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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

    @ManyToMany(mappedBy = "rightSet")
    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @ManyToOne
    @JoinColumn(name = "menu_code", foreignKey = @ForeignKey(name = "fk_menu_code"), referencedColumnName = "menuCode")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Right right = (Right) o;

        if (!getRightName().equals(right.getRightName())) return false;
        if (!getRightCode().equals(right.getRightCode())) return false;
        if (!getRightRemark().equals(right.getRightRemark())) return false;
        if (!getMenuInfo().equals(right.getMenuInfo())) return false;
        return getRightLink().equals(right.getRightLink());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        int prime = 31;
        result = prime * result + (getRightName() == null ? 0 : getRightName().hashCode());
        result = prime * result + (getRightCode() == null ? 0 : getRightCode().hashCode());
        result = prime * result + (getPermission() == null ? 0 : getPermission().hashCode());
        result = prime * result + (getRightRemark() == null ? 0 : getRightRemark().hashCode());
        result = prime * result + (getMenuInfo() == null ? 0 : getMenuInfo().hashCode());
        result = prime * result + (getRightLink() == null ? 0 : getRightLink().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return rightName;
    }
}
