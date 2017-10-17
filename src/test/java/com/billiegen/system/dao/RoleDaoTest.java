package com.billiegen.system.dao;

import com.billiegen.Application;
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

import static org.junit.Assert.assertTrue;

/**
 * @author mrdios
 * @date 2017-10-14
 */
@Transactional
@Rollback(value = false)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RoleDaoTest {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RightDao rightDao;

    private Role role = new Role();

    @Before
    public void setUp() {
        role.setRoleName("超级管理员");
        role.setDescription("拥有所有权限");
    }

    @Test
    public void save() {
        roleDao.save(role);
        System.out.println(role.getId());
    }

    @Test
    public void update() {
        Role role = roleDao.findRoleByRoleNameEquals("超级管理员");
        Right right = rightDao.findOne("f1fdb7c3245f4a919320b67de02a7992");
        Set<Right> rights = new HashSet<>();
        rights.add(right);
        role.setRightSet(rights);
        roleDao.save(role);
    }

    @Test
    public void findRoleByNameEquals() throws Exception {
        Role role = roleDao.findRoleByRoleNameEquals("超级管理员");
        assertTrue(role != null && role.getRoleName().equals("超级管理员"));
    }

    @Test
    public void delete() {
        Role role = roleDao.findRoleByRoleNameEquals("超级管理员");
        roleDao.delete(role);
    }

}