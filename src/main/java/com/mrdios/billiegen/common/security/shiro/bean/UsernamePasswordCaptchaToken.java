package com.mrdios.billiegen.common.security.shiro.bean;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author CodePorter
 * @date 2017-10-19
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {
    private String captcha;

    public UsernamePasswordCaptchaToken() {
        super();
    }

    public UsernamePasswordCaptchaToken(String username, String password,
                                        boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
