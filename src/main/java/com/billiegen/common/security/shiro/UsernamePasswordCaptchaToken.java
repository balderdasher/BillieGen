package com.billiegen.common.security.shiro;

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

    public UsernamePasswordCaptchaToken(String username, String password, String captcha) {
        super(username, password);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
