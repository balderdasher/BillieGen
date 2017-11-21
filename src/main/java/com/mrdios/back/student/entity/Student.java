package com.mrdios.back.student.entity;

import com.billiegen.common.framework.entity.BaseEntity;
import com.billiegen.system.enums.Sex;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * @author CodePorter
 * @date 2017-11-20
 */
@Entity
public class Student extends BaseEntity {
    private String name;
    private Integer age;
    private Sex sex;
    private Date birthday;

    @Override
    public void onSave() {
        super.onSave();
        if (this.sex == null) {
            this.sex = Sex.MALE;
        }
        if (this.age == null) {
            this.age = 20;
        }
        if (this.birthday == null) {
            this.birthday = new Date();
        }
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Column(nullable = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
