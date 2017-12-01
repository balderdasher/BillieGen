package com.billiegen.system.service.impl;

import com.billiegen.common.framework.service.BaseServiceImpl;
import com.billiegen.common.security.shiro.bean.Principal;
import com.billiegen.system.dao.AdminDao;
import com.billiegen.system.entity.Admin;
import com.billiegen.system.entity.Menu;
import com.billiegen.system.entity.Right;
import com.billiegen.system.service.SecurityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author CodePorter
 * @date 2017-12-01
 */
@Service
@Transactional(readOnly = true)
public class ShiroSecurityServiceImpl extends BaseServiceImpl<AdminDao, Admin, String> implements SecurityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroSecurityServiceImpl.class);
    @Autowired
    private AdminDao adminDao;

    @Override
    public Principal getLoginPrincipal() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return (Principal) subject.getPrincipal();
        }
        return null;
    }

    @Override
    public Principal getAuthorizationPrincipal(Principal principal) {
        String username = principal.getUsername();
        Set<String> roles = principal.getRoles();
        Set<String> rights = principal.getRights();
        Set<Menu> menus = principal.getMenus();
        // clear for another Authorization
        roles.clear();
        rights.clear();
        menus.clear();
        Admin user = adminDao.findAdminByUsernameEquals(username);

        user.getRoleSet().forEach(role -> {
            // add roles
            roles.add(role.getId());
            LOGGER.info("Add role:{} to {}", role.getRoleName(), username);

            // add rights
            Set<Right> rightTemp = role.getRightSet();
            rightTemp.forEach(right -> LOGGER.info("Add right:{}-{} to {}",
                    right.getRightName(), right.getPermission(), username));

            rights.addAll(rightTemp.stream()
                    .sorted(Comparator.comparingInt(Right::getSortSeq))
                    .map(Right::getPermission)
                    .collect(Collectors.toSet())
            );

            // add menus
            rightTemp.forEach(right -> menus.add(right.getMenuInfo()));
            Set<Menu> menusThree = new HashSet<>(menus);
            menusThree.forEach(menu -> {
                Menu temp = menu;
                while (temp.getParentMenu() != null && !menus.contains(temp.getParentMenu())) {
                    menus.add(temp.getParentMenu());
                    temp = temp.getParentMenu();
                }
            });
        });
        menus.forEach(menu -> LOGGER.info("Add menu:{}-[{}_{}] to {}", menu.getMenuName(),
                menu.getMenuLevel(), menu.getMenuCode(), username));
        return principal;
    }
}
