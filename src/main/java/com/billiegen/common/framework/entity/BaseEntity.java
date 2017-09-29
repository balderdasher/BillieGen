package com.billiegen.common.framework.entity;

import javax.persistence.MappedSuperclass;
import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据实体基类
 *
 * @author CodePorter
 * @date 2017-09-29
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    /* 标识ID */
    protected String id;
    /* 创建时间 */
    protected Date createDate;
    /* 修改时间 */
    protected Date modifyDate;
    /* 排列顺序 */
    protected Integer sortSeq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getSortSeq() {
        return sortSeq;
    }

    public void setSortSeq(Integer sortSeq) {
        this.sortSeq = sortSeq;
    }

    @Transient
    public void onSave() {
        if (this.sortSeq == null) {
            this.sortSeq = 0;
        }
    }

    @Transient
    public void onUpdate() {
        if (this.sortSeq == null) {
            this.sortSeq = 0;
        }
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null) return false;

        Class thisClass = this.getClass();
        Class thatClass = that.getClass();
        if ((thisClass != thatClass)
                && (thisClass.getSuperclass() != thatClass)
                && (thisClass != thatClass.getSuperclass())) {
            return false;
        }

        BaseEntity obj = (BaseEntity) that;
        return getId().equals(obj.getId());
    }

    @Override
    public int hashCode() {
        int prime = 37;
        int result = 17;

        result = prime * result + getId().hashCode();
        return result;
    }
}
