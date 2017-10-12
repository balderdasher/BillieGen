package com.billiegen.common.jpa.impl;

import com.billiegen.common.framework.entity.BaseEntity;
import com.billiegen.common.jpa.BillieRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义repository的方法接口实现类,该类主要提供自定义的公用方法,或者重写默认实现中的方法
 *
 * @author CodePorter
 * @date 2017-10-11
 */
public class BillieRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BillieRepository<T, ID> {
    private final Class<T> clazz = super.getDomainClass();
    private final JpaEntityInformation<T, ?> entityInformation;


    public BillieRepositoryImpl(JpaEntityInformation<T, ?> efm, EntityManager em) {
        super(efm, em);
        this.entityInformation = efm;
    }

    public BillieRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        try {
            Method onSave = this.clazz.getMethod("onSave");
            Method onUpdate = this.clazz.getMethod("onUpdate");
            preSaveBaseEntity(entity, onSave, onUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return super.save(entity);
    }

    private final void preSaveBaseEntity(T entity, Method onSave, Method onUpdate) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if ((entity instanceof BaseEntity)) {
            if (this.entityInformation.isNew(entity)) {
                onSave.invoke(entity);
            } else {
                onUpdate.invoke(entity);
            }
        }
    }

    @Override
    @Transactional
    public <S extends T> List<S> save(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        Method onSaveMethod;
        Method onUpdateMethod;
        if (entities == null) {
            return result;
        } else {
            try {
                onSaveMethod = this.clazz.getMethod("onSave");
                onUpdateMethod = this.clazz.getMethod("onUpdate");
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }
            for (S entity : entities) {
                try {
                    preSaveBaseEntity(entity, onSaveMethod, onUpdateMethod);
                } catch (Exception e) {
                    e.printStackTrace();
                    result.add(null);
                }
                result.add(this.save(entity));
            }
            return result;
        }
    }

    @Override
    @Transactional
    public <S extends T> S saveAndFlush(S entity) {
        return super.saveAndFlush(entity);
    }
}
