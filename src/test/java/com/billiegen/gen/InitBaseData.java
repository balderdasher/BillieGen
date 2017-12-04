package com.billiegen.gen;

import com.Application;
import com.billiegen.common.security.util.PasswordHelper;
import com.billiegen.system.dao.AdminDao;
import com.billiegen.system.dao.MenuDao;
import com.billiegen.system.dao.RightDao;
import com.billiegen.system.dao.RoleDao;
import com.billiegen.system.entity.Admin;
import com.billiegen.system.entity.Menu;
import com.billiegen.system.entity.Right;
import com.billiegen.system.entity.Role;
import com.billiegen.system.enums.Sex;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 初始化系统基础数据
 *
 * @author CodePorter
 * @date 2017-11-28
 */
@Transactional
@Rollback(value = false)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InitBaseData {
    private static final Logger logger = LoggerFactory.getLogger(InitBaseData.class);

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RightDao rightDao;
    @Autowired
    private MenuDao menuDao;

    private Menu rootMenu;
    private Admin admin;
    private Role role;
    private Set<Role> roleSet = new HashSet<>();
    private Set<Right> rightSet = new HashSet<>();

    @Test
    public void initBaseData() {
        StopWatch timer = new StopWatch();
        logger.info("System base data init begin...");
        timer.start();
        initSystemMenu();
        initSystemRight();
        initSystemRole();
        initSuperAdmin();
        timer.stop();
        logger.info("System base data init OK, It takes {} seconds...", timer.getTime(TimeUnit.SECONDS) + "");
    }

    @Test
    public void verifyBaseData() {
        Admin admin = adminDao.findAdminByUsernameEquals("admin");
        logger.info("超级管理员 [{}] is exist...", admin.getUsername());
        Set<Role> roles = admin.getRoleSet();
        Set<Right> rights = new HashSet<>();
        Set<Menu> menusThree = new HashSet<>();
        roles.forEach(role1 -> rights.addAll(role1.getRightSet()));
        rights.forEach(right -> menusThree.add(right.getMenuInfo()));
        Set<Menu> menus = new HashSet<>(menusThree);
        menusThree.forEach(menu -> {
            Menu temp = menu;
            while (temp.getParentMenu() != null && !menus.contains(temp.getParentMenu())) {
                menus.add(temp.getParentMenu());
                temp = temp.getParentMenu();
            }
        });
        logger.info("超级管理员拥有角色 {}", roles);
        logger.info("超级管理员拥有权限 {}", rights);
        logger.info("超级管理员拥有菜单 {}", menus);
    }

    // 菜单
    private void initSystemMenu() {
        rootMenu = menuDao.findByMenuCode("0000");
        if (rootMenu == null) {
            rootMenu = new Menu();
            rootMenu.setMenuCode("0000");
            rootMenu.setMenuName("系统功能");
            rootMenu.setMenuLevel(0);
            rootMenu.setMenuLink("/admin");
            rootMenu.setMenuRemark("后台功能根菜单");
            menuDao.save(rootMenu);
            logger.info("The root menu is not exist,create new one.....");
        }

        int menuCodeOne = Integer.valueOf(menuDao.getNextMenuCode(1));
        int menuCodeTwo = Integer.valueOf(menuDao.getNextMenuCode(2));
        int menuCodeThree = Integer.valueOf(menuDao.getNextMenuCode(3));

        Menu menuOne = menuDao.findByMenuName("系统设置");
        if (menuOne != null) {
            logger.info("菜单 [系统设置] is exist.....");
        } else {
            menuOne = new Menu();
            menuOne.setMenuName("系统设置");
            menuOne.setMenuLevel(1);
            menuOne.setMenuLink("/sys");
            menuOne.setMenuCode(menuCodeOne + "");
            menuOne.setParentMenu(rootMenu);
            menuDao.save(menuOne);
            logger.info("菜单 [系统设置] is not exist, create new one.....");
        }

        Menu menuTwo = menuDao.findByMenuName("基础管理");
        if (menuTwo != null) {
            logger.info("菜单 [基础管理] is exist.....");
        } else {
            menuTwo = new Menu();
            menuTwo.setMenuName("基础管理");
            menuTwo.setMenuLevel(2);
            menuTwo.setMenuLink("");
            menuTwo.setMenuCode(menuCodeTwo + "");
            menuTwo.setParentMenu(menuOne);
            menuDao.save(menuTwo);
            logger.info("菜单 [基础管理] is not exist, create new one.....");
        }
        Menu adminMenu = menuDao.findByMenuName("管理员管理");
        if (adminMenu != null) {
            logger.info("菜单 [管理员管理] is exist.....");
        } else {
            adminMenu = new Menu();
            adminMenu.setMenuName("管理员管理");
            adminMenu.setMenuLevel(3);
            adminMenu.setMenuLink("/sys/admin");
            adminMenu.setMenuCode(menuCodeThree++ + "");
            adminMenu.setParentMenu(menuTwo);
            menuDao.save(adminMenu);
            logger.info("菜单 [管理员管理] is not exist, create new one.....");
        }

        Menu roleMenu = menuDao.findByMenuName("角色管理");
        if (roleMenu != null) {
            logger.info("菜单 [角色管理] is exist.....");
        } else {
            roleMenu = new Menu();
            roleMenu.setMenuName("角色管理");
            roleMenu.setMenuLevel(3);
            roleMenu.setMenuLink("/sys/role");
            roleMenu.setMenuCode(menuCodeThree++ + "");
            roleMenu.setParentMenu(menuTwo);
            menuDao.save(roleMenu);
            logger.info("菜单 [角色管理] is not exist, create new one.....");
        }

        Menu rightMenu = menuDao.findByMenuName("权限管理");
        if (rightMenu != null) {
            logger.info("菜单 [权限管理] is exist.....");
        } else {
            rightMenu = new Menu();
            rightMenu.setMenuName("权限管理");
            rightMenu.setMenuLevel(3);
            rightMenu.setMenuLink("/sys/right");
            rightMenu.setMenuCode(menuCodeThree++ + "");
            rightMenu.setParentMenu(menuTwo);
            menuDao.save(rightMenu);
            logger.info("菜单 [权限管理] is not exist, create new one.....");
        }

        Menu menuMenu = menuDao.findByMenuName("菜单管理");
        if (menuMenu != null) {
            logger.info("菜单 [菜单管理] is exist.....");
        } else {
            menuMenu = new Menu();
            menuMenu.setMenuName("菜单管理");
            menuMenu.setMenuLevel(3);
            menuMenu.setMenuLink("/sys/menu");
            menuMenu.setMenuCode(menuCodeThree++ + "");
            menuMenu.setParentMenu(menuTwo);
            menuDao.save(menuMenu);
            logger.info("菜单 [权限管理] is not exist, create new one.....");
        }
    }

    // 权限
    private void initSystemRight() {
        List<Menu> menuThree = menuDao.findByMenuLevel(3);
        AtomicInteger rightCode = new AtomicInteger(Integer.valueOf(rightDao.getNextRightCode()));
        if (!menuThree.isEmpty()) {
            menuThree.forEach(menu -> {
                for (ActType type : ActType.values()) {
                    String rightLink = menu.getMenuLink() + "/" + type.toString().toLowerCase();
                    String permission = rightLink.replaceAll("/", ":").substring(1);
                    Right right = rightDao.findByRightLink(rightLink);
                    if (right != null) {
                        logger.info("权限 [{}] is exist.....", right.getRightName());
                    } else {
                        right = new Right();
                        right.setRightName(menu.getMenuName() + "_" + type);
                        right.setRightCode(rightCode.getAndIncrement() + "");
                        right.setPermission(permission);
                        right.setRightLink(rightLink);
                        right.setMenuInfo(menu);
                        rightDao.save(right);
                        logger.info("权限 [{}] is not exist, create new one.....", right.getRightName());
                    }
                    rightSet.add(right);
                }
            });
        }
    }

    // 角色
    private void initSystemRole() {
        role = roleDao.findRoleByRoleNameEquals("超级管理员");
        if (role != null) {
            logger.info("角色 [超级管理员] is exist, try to update id.....");
        } else {
            role = new Role();
            role.setRoleName("超级管理员");
            role.setDescription("拥有所有权限");
            logger.info("角色 [超级管理员] is not exist, create new one.....");
        }
        role.setRightSet(rightSet);
        roleDao.save(role);
        roleSet.add(role);
    }

    // 管理员
    private void initSuperAdmin() {
        admin = adminDao.findAdminByUsernameEquals("admin");
        if (admin != null) {
            logger.info("管理员 [超级管理员] is exist, try to update it.....");
        } else {
            admin = new Admin();
            admin.setUsername("admin");
            admin.setNickname("CodePorter");
            admin.setRealname("CodePorter");
            admin.setPassword(PasswordHelper.entryptPassword("123456"));
            admin.setSuper(true);
            admin.setSex(Sex.MALE);
            admin.setEmail("balderdasher@msn.com");
            admin.setMobileNumber("13366383563");
            admin.setWebsiteUrl("http://www.mrdios.com");
            admin.setOccupation("CodePorter");
            logger.info("管理员 [超级管理员] is not exist, create new one.....");
        }
        admin.setRoleSet(roleSet);
        adminDao.save(admin);
    }

    enum ActType {
        ADD, DELETE, UPDATE, VIEW
    }
}
