package com.billiegen.common.security.shiro;

import com.billiegen.system.dao.AdminDao;
import com.billiegen.system.entity.Admin;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author CodePorter
 * @date 2017-10-19
 */
public class BillieShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private AdminDao adminDao;

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authenticationToken;
        logger.info("{} is trying to authentication", token.getUsername());

        // 验证码校验
        String captchaRight = (String) SecurityUtils.getSubject().getSession(true).getAttribute("captcha");
        String captchaInput = token.getCaptcha();
        if (StringUtils.isEmpty(captchaInput) || !StringUtils.equalsIgnoreCase(captchaInput, captchaRight)) {
            throw new AuthenticationException("验证码错误.");
        }
        // 用户名密码校验
        Admin user = adminDao.findAdminByUsernameEquals(token.getUsername());
        if (user != null) {
            if (!user.getEnabled()) {
                throw new AuthenticationException("账户未启用.");
            }
            if (user.getLocked()) {
                throw new AuthenticationException("账号被锁定.");
            }
            return new SimpleAuthenticationInfo();
        }
        return null;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
