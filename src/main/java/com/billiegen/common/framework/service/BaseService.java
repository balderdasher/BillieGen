package com.billiegen.common.framework.service;

import com.billiegen.common.jpa.BillieRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author CodePorter
 * @date 2017-09-29
 */
@NoRepositoryBean
public interface BaseService<T, ID extends Serializable> extends BillieRepository<T, ID> {

}
