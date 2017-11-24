package com.billiegen.system.entity;

import com.billiegen.common.framework.entity.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 *
 * @author CodePorter
 * @date 2017-09-30
 */
@Entity
@Table(name = "sys_menu",
        indexes = {
                @Index(columnList = "menuCode", name = "uk_menu_code", unique = true),
                @Index(columnList = "parent_menu", name = "idx_menu_parent_menu")
        }
)
public class Menu extends BaseEntity {
    private String menuName;
    private String menuLink;
    private String menuRemark;
    private String menuCode;
    private String menuLevel;
    private Menu parentMenu;
    private List<Menu> subMenu = new ArrayList<>();
    private List<Right> allRights = new ArrayList<>();

    @Column(nullable = false, length = 20)
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Column(nullable = false)
    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    @Column(length = 1024)
    public String getMenuRemark() {
        return menuRemark;
    }

    public void setMenuRemark(String menuRemark) {
        this.menuRemark = menuRemark;
    }

    @Column(nullable = false, length = 4)
    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Column(nullable = false)
    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    @ManyToOne
    @JoinColumn(name = "parent_menu", foreignKey = @ForeignKey(name = "fk_parent_menu"), referencedColumnName = "menuCode")
    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    @OneToMany(targetEntity = Menu.class, cascade = CascadeType.ALL, mappedBy = "parentMenu")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("sortSeq asc")
    public List<Menu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<Menu> subMenu) {
        this.subMenu = subMenu;
    }

    @OneToMany(targetEntity = Right.class, cascade = CascadeType.ALL, mappedBy = "menuInfo")
    @OrderBy("sortSeq asc")
    public List<Right> getAllRights() {
        return allRights;
    }

    public void setAllRights(List<Right> allRights) {
        this.allRights = allRights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Menu menu = (Menu) o;

        if (!getMenuName().equals(menu.getMenuName())) return false;
        if (!getMenuLink().equals(menu.getMenuLink())) return false;
        if (!getMenuRemark().equals(menu.getMenuRemark())) return false;
        if (!getMenuCode().equals(menu.getMenuCode())) return false;
        return getMenuLevel().equals(menu.getMenuLevel());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        int prime = 31;
        result = prime * result + getMenuName().hashCode();
        result = prime * result + getMenuLink().hashCode();
        result = prime * result + getMenuRemark().hashCode();
        result = prime * result + getMenuCode().hashCode();
        result = prime * result + getMenuLevel().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuName='" + menuName + '\'' +
                '}';
    }
}
