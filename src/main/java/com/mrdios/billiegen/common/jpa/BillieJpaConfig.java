package com.mrdios.billiegen.common.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * @author CodePorter
 * @date 2017-10-11
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.mrdios.billiegen.**.dao",
        repositoryFactoryBeanClass = BillieRepositoryFactoryBean.class)
@EnableSpringDataWebSupport
public class BillieJpaConfig {
}
