package com.billiegen.common.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 自定义Repository接口
 *
 * @author CodePorter
 * @date 2017-10-11
 */
@NoRepositoryBean
public interface BillieRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
