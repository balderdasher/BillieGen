package com.billiegen.common.framework.service;

import com.billiegen.common.framework.dao.BaseDao;
import com.billiegen.common.framework.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author CodePorter
 * @date 2017-11-22
 */
@Transactional
public class BaseServiceImpl<D extends BaseDao<T, ID>, T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {
    @Autowired
    protected D dao;

    @Override
    public List<T> findAll() {
        return this.dao.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return this.dao.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return this.dao.findAll(pageable);
    }

    @Override
    public List<T> findAll(Iterable<ID> iterable) {
        return this.dao.findAll(iterable);
    }

    @Override
    public long count() {
        return this.dao.count();
    }

    @Override
    public void delete(ID id) {
        dao.delete(id);
    }

    @Override
    public void delete(T t) {
        dao.delete(t);
    }

    @Override
    public void delete(Iterable<? extends T> iterable) {
        dao.delete(iterable);
    }

    @Override
    public void deleteSafely(ID id) {
        T entity = findOne(id);
        if (entity == null) {
            throw new RuntimeException("the data is not exist with id:" + id);
        }
        entity.setDelFlag(true);
        this.save(entity);
    }

    @Override
    public void deleteSafely(T t) {
        if (t == null) {
            return;
        }
        delete((ID) t.getId());
    }

    @Override
    public void deleteSafely(Iterable<? extends T> iterable) {
        if (null == iterable) {
            return;
        }
        for (T entity : iterable) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        this.dao.deleteAll();
    }

    @Override
    public <S extends T> S save(S s) {
        return this.dao.save(s);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> iterable) {
        return this.dao.save(iterable);
    }

    @Override
    public T findOne(ID id) {
        return this.dao.findOne(id);
    }

    @Override
    public boolean exists(ID id) {
        return this.dao.exists(id);
    }

    @Override
    public void flush() {
        this.dao.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S s) {
        return this.dao.saveAndFlush(s);
    }

    @Override
    public void deleteInBatch(Iterable<T> iterable) {
        this.dao.deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        this.dao.deleteAllInBatch();
    }

    @Override
    public T getOne(ID id) {
        return this.dao.getOne(id);
    }

    @Override
    public <S extends T> S findOne(Example<S> example) {
        return dao.findOne(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return dao.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return dao.findAll(example, sort);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return dao.findAll(example, pageable);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return dao.count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return dao.exists(example);
    }
}
