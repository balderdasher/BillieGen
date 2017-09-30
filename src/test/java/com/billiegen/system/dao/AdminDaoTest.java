package com.billiegen.system.dao;

import com.billiegen.Application;
import com.billiegen.system.entity.Admin;
import com.billiegen.system.enums.Sex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author CodePorter
 * @date 2017-09-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AdminDaoTest {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void save(){
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("123");
        admin.setSex(Sex.MALE);
        admin.setEmail("balderdasher@msn.com");
        adminDao.save(admin);
        System.out.println(admin.getId());
    }
}