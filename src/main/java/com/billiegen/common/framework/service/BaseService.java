package com.billiegen.common.framework.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @author CodePorter
 * @date 2017-09-29
 */
public interface BaseService<T, ID extends Serializable> extends JpaRepository {

}
