package com.billiegen.common.security.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author CodePorter
 * @date 2017-10-23
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
    private static final Logger logger = LogManager.getLogger();
    public static final String FILTER_KEY = "authc";
    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    public static final String DEFAULT_MESSAGE_PARAM = "shiroLoginFailMessage";

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = WebUtils.getCleanParam(request, DEFAULT_CAPTCHA_PARAM);
        StringUtils.defaultIfBlank(password, StringUtils.EMPTY);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new UsernamePasswordCaptchaToken(username, password, rememberMe, host, captcha);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String exceptionName = e.getClass().getName();
        String message;
        if (UnknownAccountException.class.getName().equals(exceptionName)
                || IncorrectCredentialsException.class.getName().equals(exceptionName)) {
            message = "用户名/密码错误.";
        } else if (StringUtils.isNotEmpty(e.getMessage())) {
            message = e.getMessage();
        } else {
            message = "系统请您稍后再试.";
            logger.error("system login exception::{}", e);
        }
        request.setAttribute(DEFAULT_MESSAGE_PARAM, message);
        return super.onLoginFailure(token, e, request, response);
    }
}
