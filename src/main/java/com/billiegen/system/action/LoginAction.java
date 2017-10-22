package com.billiegen.system.action;

import com.billiegen.common.security.shiro.Principal;
import com.billiegen.common.security.shiro.UsernamePasswordCaptchaToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author CodePorter
 * @date 2017-10-20
 */
@Controller
@RequestMapping("${billie.common.config.admin.path}")
public class LoginAction {
    private Logger logger = LogManager.getLogger();

    @Value("${billie.common.config.admin.path}")
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
        String error = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(username, password, captcha);
        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = e.getMessage();
        }
        if (StringUtils.isEmpty(error)) {
            return home();
        }
        logger.info(error);
        model.addAttribute("msg", error);
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
        Subject subject = SecurityUtils.getSubject();
        Principal principal = (Principal) subject.getPrincipal();
        if (principal == null) {
            return "page_user_login_1";
        }
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
    @ResponseBody
    public Map<String, Object> logout() {
        Map<String, Object> resuleMap = new LinkedHashMap<String, Object>();
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resuleMap;
    }
}
