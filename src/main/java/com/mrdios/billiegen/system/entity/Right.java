package com.mrdios.billiegen.system.entity;

import com.mrdios.billiegen.common.framework.entity.BaseEntity;

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
                @Index(columnList = "menu_id", name = "idx_menu_id")
        }
)
public class Right extends BaseEntity {
    private String rightName;
    private String rightCode;
    private String rightRemark;
    private Set<Role> roleSet;
    private Menu menuInfo;
    private String rightLink;

    @Column(nullable = false)
    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    @Column(nullable = false, updatable = false)
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

    @ManyToMany(mappedBy = "rightSet")
    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @ManyToOne
    @JoinColumn(name = "menu_id", foreignKey = @ForeignKey(name = "fk_menu_id"))
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
