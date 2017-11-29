package com.billiegen.common.framework.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @author CodePorter
 * @date 2017-09-29
 */
public interface BaseService<T, ID extends Serializable> {
    public List<T> findAll();

    public List<T> findAll(Sort sort);

    public Page<T> findAll(Pageable pageable);

    public List<T> findAll(Iterable<ID> iterable);

    public long count();

    public void delete(ID id);

    public void delete(T t);

    public void delete(Iterable<? extends T> iterable);

    public void deleteSafely(ID id);

    public void deleteSafely(T t);

    public void deleteSafely(Iterable<? extends T> iterable);

    public void deleteAll();

    public <S extends T> S save(S s);

    public <S extends T> List<S> save(Iterable<S> iterable);

    public T findOne(ID id);

    public boolean exists(ID id);

    public void flush();

    public <S extends T> S saveAndFlush(S s);

    public void deleteInBatch(Iterable<T> iterable);

    public void deleteAllInBatch();

    public T getOne(ID id);

    public <S extends T> S findOne(Example<S> example);

    public <S extends T> List<S> findAll(Example<S> example);

    public <S extends T> List<S> findAll(Example<S> example, Sort sort);

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

    public <S extends T> long count(Example<S> example);

    public <S extends T> boolean exists(Example<S> example);
}
