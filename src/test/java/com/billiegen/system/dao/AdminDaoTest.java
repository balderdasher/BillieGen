package com.billiegen.system.dao;

import com.billiegen.Application;
import com.billiegen.system.entity.Admin;
import com.billiegen.system.enums.Sex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;

/**
 * @author CodePorter
 * @date 2017-09-30
 */
@Transactional
@Rollback(value = false)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
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
        Admin admin = adminDao.findOne("1d768f55b8014519bef7873581ec9ff5");
        assertTrue(admin != null);
    }

    @Test
    public void update() {
        String id = "1d768f55b8014519bef7873581ec9ff5";
        Admin admin = adminDao.findOne(id);
        admin.setSex(Sex.FEMALE);
        adminDao.save(admin);
        Admin updator = adminDao.findOne(id);
        assertTrue(updator.getSex() == Sex.FEMALE);
    }

    @Test
    public void delete() {
        String delId = "1d768f55b8014519bef7873581ec9ff5";
        adminDao.delete(delId);
        assertTrue(adminDao.findOne(delId) == null);
    }
}