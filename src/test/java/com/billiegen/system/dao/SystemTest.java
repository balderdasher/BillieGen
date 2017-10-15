package com.billiegen.system.dao;

import com.billiegen.Application;
import com.billiegen.system.entity.Admin;
import com.billiegen.system.entity.Menu;
import com.billiegen.system.entity.Right;
import com.billiegen.system.entity.Role;
import com.billiegen.system.enums.Sex;
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
        role = roleDao.findOne("c41b823d583a4b86abef08452cfd67fd");
        roleSet.add(role);
    }

    @Test
    public void adminRoleSaveTest() {
        admin.setUsername("admin");
        admin.setPassword("123");
        admin.setSex(Sex.MALE);
        admin.setEmail("balderdasher@msn.com");
        admin.setRoleSet(roleSet);
        adminDao.save(admin);
        System.out.println(admin.getId());
    }

    @Test
    public void adminRoleUpdateTest(){
        Admin admin = adminDao.findOne("017156a796e64167aba92661c147f42e");
        roleSet.add(role);
        admin.setRoleSet(roleSet);
        adminDao.save(admin);
    }

    @Test
    public void adminRoleDeleteTest(){
        Admin admin = adminDao.findOne("017156a796e64167aba92661c147f42e");
        adminDao.delete(admin);
    }

    @Test
    public void roleRightSaveTest(){

    }
}
