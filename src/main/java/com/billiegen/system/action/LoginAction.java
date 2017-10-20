package com.billiegen.system.action;

import com.billiegen.common.security.shiro.Principal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
@RequestMapping("{billie.common.config.admin.path}")
public class LoginAction {
    private Logger logger = LogManager.getLogger();

    @Value("{billie.common.config.admin.path}")
    private String adminPath;

    @GetMapping("/login")
    public String toLogin(Model model, HttpServletRequest request) {
        model.addAttribute("username", "billiegen");
        Subject subject = SecurityUtils.getSubject();
        Principal principal = (Principal) subject.getPrincipal();
        if (principal != null) {
            return home();
        }
        return "page_user_login_1";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        // 登录失败从request中获取shiro处理的异常信息
        // shiro异常类全类名：shiroLoginFailure
        String exception = (String) request.getAttribute("shiroLoginFailure");
        logger.info("shiro exception is {}", exception);
        String msg = "";
        if (!StringUtils.isEmpty(exception)) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "UnknownAccountException --> 账号不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "IncorrectCredentialsException --> 密码不对";
            } else if ("kaptchaValidateFaild".equals(exception)) {
                msg = "kaptchaValidateFaild --> 验证码不对";
            } else {
                msg = "验证异常 >> " + exception;
            }
        }
        logger.info(msg);
        model.addAttribute("msg", msg);
        // 此方法不处理登录成功，由shiro进行处理
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
        model.addAttribute("time", new Date());
        return "index";
    }

    @RequestMapping("")
    public String home() {
        return "redirect:" + adminPath + "/index";
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
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
