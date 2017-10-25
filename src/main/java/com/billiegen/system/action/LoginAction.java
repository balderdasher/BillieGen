package com.billiegen.system.action;

import com.billiegen.common.security.shiro.bean.Principal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CodePorter
 * @date 2017-10-20
 */
@Controller
@RequestMapping("${billie.back.path}")
public class LoginAction {
    private Logger logger = LogManager.getLogger();

    @Value("${billie.back.path}")
    private String adminPath;

    @GetMapping("/login")
    public String toLogin() {
        Subject subject = SecurityUtils.getSubject();
        Principal principal = (Principal) subject.getPrincipal();
        if (principal != null) {
            return home();
        }
        return "page_user_login_1";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        return "page_user_login_1";
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("")
    public String home() {
        return "redirect:" + adminPath + "/index";
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
