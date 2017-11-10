package com.mrdios.billiegen.common.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

/**
 * 自定义UUID生成器去掉hibernate生成出来的'-'
 *
 * @author CodePorter
 * @date 2017-10-12
 */
public class BillieUUIDGenerator extends UUIDGenerator {
    public BillieUUIDGenerator() {
    }

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        String uuid = super.generate(session, object).toString();
        return uuid.replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println("6f26158f-937d-4efa-86cd-7f745479b3f4".replaceAll("-", ""));
    }

}
