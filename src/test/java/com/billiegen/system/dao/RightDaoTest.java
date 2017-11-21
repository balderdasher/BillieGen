package com.billiegen.system.dao;

import com.Application;
import com.billiegen.system.entity.Menu;
import com.billiegen.system.entity.Right;
import com.billiegen.system.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

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
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private RoleDao roleDao;

    private Role role;
    private Menu menu;
    private Set<Role> roleSet = new HashSet<>();


    @Before
    public void setUp() throws Exception {
        role = roleDao.findRoleByRoleNameEquals("超级管理员");
        roleSet.add(role);
        menu = menuDao.findOne("e389499c22c74ef4bc8a8e82dd97c774");
    }

    @Test
    public void save() {
        Right right = new Right();
        right.setRightName("管理员管理");
        right.setRightCode("0010");
        right.setRightLink("/sys/admin");
        right.setMenuInfo(menu);
        rightDao.save(right);
        right.setRoleSet(roleSet);
        rightDao.save(right);
    }

}