package com.billiegen.common.jpa;

import com.billiegen.common.jpa.impl.BillieRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 创建一个自定义的FactoryBean去替代默认的工厂类
 *
 * @author CodePorter
 * @date 2017-10-11
 */
public class BillieRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, ID> {

    public BillieRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new BillieRepositoryFactory<>(em);
    }

    private static class BillieRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {
        private final EntityManager em;

        public BillieRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.em = entityManager;
        }

        @Override
        protected Object getTargetRepository(RepositoryInformation  information) {
            return new BillieRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), em);
        }

        @Override
        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
            return new BillieRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), em);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BillieRepositoryImpl.class;
        }
    }
}
