package com.billiegen.common.framework.service;

import com.billiegen.common.framework.dao.BaseDao;
import com.billiegen.common.framework.entity.BaseEntity;
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
public class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {
    private BaseDao<T, ID> baseDao;

    public BaseDao<T, ID> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<T> findAll() {
        return this.baseDao.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return this.baseDao.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return this.baseDao.findAll(pageable);
    }

    @Override
    public List<T> findAll(Iterable<ID> iterable) {
        return this.baseDao.findAll(iterable);
    }

    @Override
    public long count() {
        return this.baseDao.count();
    }

    @Override
    public void delete(ID id) {
        T entity = findOne(id);
        if (entity == null) {
            throw new RuntimeException("the data is not exist with id:" + id);
        }
        BaseEntity e = (BaseEntity) entity;
        e.setDelFlag(true);
        this.save((T) e);
    }

    @Override
    public void delete(T t) {
        if (t == null) {
            return;
        }
        BaseEntity e = (BaseEntity) t;
        delete((ID) e.getId());
    }

    @Override
    public void delete(Iterable<? extends T> iterable) {
        if (null == iterable) {
            return;
        }
        for (T entity : iterable) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        this.baseDao.deleteAll();
    }

    @Override
    public <S extends T> S save(S s) {
        return this.baseDao.save(s);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> iterable) {
        return this.baseDao.save(iterable);
    }

    @Override
    public T findOne(ID id) {
        return this.baseDao.findOne(id);
    }

    @Override
    public boolean exists(ID id) {
        return this.baseDao.exists(id);
    }

    @Override
    public void flush() {
        this.baseDao.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S s) {
        return this.baseDao.saveAndFlush(s);
    }

    @Override
    public void deleteInBatch(Iterable<T> iterable) {
        this.baseDao.deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        this.baseDao.deleteAllInBatch();
    }

    @Override
    public T getOne(ID id) {
        return this.baseDao.getOne(id);
    }

    @Override
    public <S extends T> S findOne(Example<S> example) {
        return baseDao.findOne(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return baseDao.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return baseDao.findAll(example, sort);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return findAll(example, pageable);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return baseDao.count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return baseDao.exists(example);
    }
}
