package com.billiegen.common.jpa;

import com.billiegen.common.jpa.impl.BillieRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * @author CodePorter
 * @date 2017-10-11
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.**.dao",
        repositoryBaseClass = BillieRepositoryImpl.class)
//@EnableJpaRepositories(basePackages = "com.**.dao",
//        repositoryFactoryBeanClass = BillieRepositoryFactoryBean.class)
@EnableSpringDataWebSupport
@EntityScan(value = "com.**.entity")
public class BillieJpaConfig {
}
