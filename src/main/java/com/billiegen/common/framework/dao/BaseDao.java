package com.billiegen.common.framework.dao;

import com.billiegen.common.jpa.BillieRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author CodePorter
 * @date 2017-09-29
 */
@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends BillieRepository<T, ID> {
}
