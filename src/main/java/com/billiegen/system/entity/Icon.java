package com.billiegen.system.entity;

import com.billiegen.common.framework.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author CodePorter
 * @date 2017-12-06
 */
@Entity
@Table(name = "sys_icon",
        indexes = {
                @Index(columnList = "className", name = "uk_classname", unique = true)
        }
)
public class Icon extends BaseEntity {
    private String displayName;
    private String className;
    private String sourceType;
    private String iconType;

    @Column(length = 64, nullable = false)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(length = 64, nullable = false)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column(length = 16)
    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @Column(length = 16)
    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    @Override
    public String toString() {
        return sourceType + "[" + displayName + "]";
    }
}
