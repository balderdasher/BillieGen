package com.billiegen.system.dao;

import com.Application;
import com.billiegen.system.entity.Menu;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mrdios
 * @date 2017-10-15
 */
@Transactional
@Rollback(value = false)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MenuDaoTest {

    @Autowired
    private MenuDao menuDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void save() {
        Menu parent = new Menu();
        parent.setMenuName("系统设置");
        parent.setMenuLevel("1");
        parent.setMenuLink("/sys");
        parent.setMenuCode("0010");

        Menu adminMenu = new Menu();
        adminMenu.setMenuName("管理员管理");
        adminMenu.setMenuLevel("2");
        adminMenu.setMenuLink("/sys/admin");
        adminMenu.setMenuCode("0011");
        adminMenu.setParentMenu(parent);
        Menu roleMenu = new Menu();
        roleMenu.setMenuName("角色管理");
        roleMenu.setMenuLevel("2");
        roleMenu.setMenuLink("/sys/role");
        roleMenu.setMenuCode("0012");
        roleMenu.setParentMenu(parent);
        Menu rightMenu = new Menu();
        rightMenu.setMenuName("权限管理");
        rightMenu.setMenuLevel("2");
        rightMenu.setMenuLink("/sys/right");
        rightMenu.setMenuCode("0013");
        rightMenu.setParentMenu(parent);
        Menu menuMenu = new Menu();
        menuMenu.setMenuName("菜单管理");
        menuMenu.setMenuLevel("2");
        menuMenu.setMenuLink("/sys/menu");
        menuMenu.setMenuCode("0014");
        menuMenu.setParentMenu(parent);

        List<Menu> sub = new ArrayList<>();
        sub.add(menuDao.save(adminMenu));
        sub.add(menuDao.save(roleMenu));
        sub.add(menuDao.save(rightMenu));
        sub.add(menuDao.save(menuMenu));
        parent.setSubMenu(sub);
        menuDao.save(parent);
    }

    @Test
    public void find(){
        Menu menu = menuDao.findOne("f2c328b26db248998d0a51862abdc8d9");
        System.out.println(menu.getSubMenu().size());
    }

    @Test
    public void deleteAll(){
        menuDao.delete("f2c328b26db248998d0a51862abdc8d9");
    }

}