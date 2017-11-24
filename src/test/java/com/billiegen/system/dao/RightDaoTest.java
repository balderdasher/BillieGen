package com.billiegen.system.dao;

import com.Application;
import com.billiegen.system.entity.Right;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * @author mrdios
 * @date 2017-10-15
 */
@Transactional
@Rollback(value = false)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RightDaoTest {

    @Autowired
    private RightDao rightDao;

    @Test
    public void save() {
        Right right = new Right();
        right.setRightName("管理员管理");
        right.setRightCode("sys:admin");
        right.setRightLink("/sys/admin");
        rightDao.save(right);
    }

    @Test
    public void delete() {
        Right right = rightDao.findRightByRightNameEquals("管理员管理");
        rightDao.delete(right);
    }

}