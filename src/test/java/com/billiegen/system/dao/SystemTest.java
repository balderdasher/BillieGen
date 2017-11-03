package com.billiegen.system.dao;

import com.Application;
import com.billiegen.system.entity.Admin;
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
 * 后台基础设施测试
 *
 * @author mrdios
 * @date 2017-10-15
 */
@Transactional
@Rollback(value = false)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SystemTest {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RightDao rightDao;
    @Autowired
    private MenuDao menuDao;

    private Menu menu = new Menu();
    private Admin admin = new Admin();
    private Role role = new Role();
    private Right right = new Right();
    private Set<Role> roleSet = new HashSet<>();
    private Set<Right> rightSet = new HashSet<>();

    @Before
    public void setUp(){
        admin = adminDao.findAdminByUsernameEquals("admin");
        role = roleDao.findRoleByRoleNameEquals("超级管理员");
        roleSet.add(role);
    }

    @Test
    public void adminRoleSaveTest() {
        admin.setRoleSet(roleSet);
        adminDao.save(admin);
        System.out.println(admin.getId());
    }

    @Test
    public void adminRoleUpdateTest(){
        admin.setRoleSet(null);
        adminDao.save(admin);
    }

    @Test
    public void delteAdminForWatchingRole() {
        adminDao.delete(admin);
    }

    @Test
    public void deleteRoleForWatchingAdmin() {
        roleDao.delete(role);
    }

    @Test
    public void roleRightSaveTest(){

    }
}
