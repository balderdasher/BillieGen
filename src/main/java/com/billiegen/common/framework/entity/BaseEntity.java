package com.billiegen.common.framework.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    /* 删除标记 */
    protected Boolean delFlag;

    @Id
    @GeneratedValue(generator = "billiegen-uuid-generator")
    @GenericGenerator(name = "billiegen-uuid-generator", strategy = "com.billiegen.common.hibernate.BillieUUIDGenerator")
//    @GenericGenerator(name = "billiegen-uuid-generator", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "gmt_create", updatable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "gmt_modified")
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

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Transient
    public void onSave() {
        if (this.sortSeq == null) {
            this.sortSeq = 0;
        }
        if (this.delFlag == null) {
            this.delFlag = false;
        }
        Date time = new Date();
        if (this.createDate == null) {
            this.createDate = time;
        }
        if (this.modifyDate == null) {
            this.modifyDate = time;
        }
    }

    @Transient
    public void onUpdate() {
        if (this.sortSeq == null) {
            this.sortSeq = 0;
        }
        if (this.delFlag == null) {
            this.delFlag = false;
        }
        this.modifyDate = new Date();
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
