package com.mrdios.back;

import com.billiegen.common.security.shiro.BillieShiroRealm;
import com.billiegen.common.security.shiro.bean.Principal;
import com.billiegen.system.service.SecurityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CodePorter
 * @date 2017-10-20
 */
@Controller
public class LoginAction {
    private Logger logger = LogManager.getLogger();

    @Value("${billie.back.path}")
    private String adminPath;

    private final SecurityService securityService;

    private final BillieShiroRealm billieShiroRealm;

    @Autowired
    public LoginAction(SecurityService securityService, BillieShiroRealm authorizingRealm) {
        this.securityService = securityService;
        this.billieShiroRealm = authorizingRealm;
    }

    @GetMapping("/login")
    public String toLogin(Model model) {
        Principal principal = securityService.getLoginPrincipal();
        if (principal != null) {
            return index(model);
        }
        return "page_user_login_1";
    }

    @PostMapping("/login")
    public String login() {
        return "page_user_login_1";
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping({"/index", ""})
    public String index(Model model) {
        Principal principal = securityService.getLoginPrincipal();
        // 第一次进入首页时手动获取授权以便设置菜单、角色、权限等信息
        if (!securityService.isPrincipalAuthorized()) {
            billieShiroRealm.getAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        }
        model.addAttribute("menus", principal.getMenus());
        return "index";
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject != null) {
                subject.logout();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "redirect:" + adminPath + "/login";
    }
}
