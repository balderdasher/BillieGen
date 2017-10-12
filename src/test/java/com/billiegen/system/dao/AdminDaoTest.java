package com.billiegen.system.dao;

import com.billiegen.Application;
import com.billiegen.system.entity.Admin;
import com.billiegen.system.enums.Sex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import static org.junit.Assert.*;

/**
 * @author CodePorter
 * @date 2017-09-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Rollback(value = true)
public class AdminDaoTest {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void save() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("123");
        admin.setSex(Sex.MALE);
        admin.setEmail("balderdasher@msn.com");
        adminDao.save(admin);
        System.out.println(admin.getId());
    }

    @Test
    public void findOne() {
        Admin admin = adminDao.findOne("2c9298a55f0fbfd9015f0fbfee1d0000");
        assertTrue(admin != null);
    }

    @Test
    public void update() {
        String id = "2c9298a55f0fbfd9015f0fbfee1d0000";
        Admin admin = adminDao.findOne(id);
        admin.setSex(Sex.FEMALE);
        adminDao.save(admin);
        Admin updator = adminDao.findOne(id);
        assertTrue(updator.getSex() == Sex.FEMALE);
    }

    @Test
    public void delete() {
        String delId = "2c9298a55f0fbfd9015f0fbfee1d0000";
        adminDao.delete(delId);
        assertTrue(adminDao.findOne(delId) == null);
    }
}