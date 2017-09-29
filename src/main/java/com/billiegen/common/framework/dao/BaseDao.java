package com.billiegen.common.framework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @author CodePorter
 * @date 2017-09-29
 */
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
