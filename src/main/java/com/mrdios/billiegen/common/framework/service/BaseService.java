package com.mrdios.billiegen.common.framework.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author CodePorter
 * @date 2017-09-29
 */
@NoRepositoryBean
public interface BaseService<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
