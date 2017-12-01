package com.billiegen.common.security.shiro;

import com.billiegen.common.security.shiro.bean.Principal;
import com.billiegen.common.security.shiro.bean.UsernamePasswordCaptchaToken;
import com.billiegen.system.dao.AdminDao;
import com.billiegen.system.entity.Admin;
import com.billiegen.system.service.SecurityService;
import com.billiegen.utils.security.EncodeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author CodePorter
 * @date 2017-10-19
 */
public class BillieShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private SecurityService securityService;
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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authenticationToken;
        logger.info("{} is trying to authentication", token.getUsername());
        Admin user = adminDao.findAdminByUsernameEquals(token.getUsername());
        if (user != null) {
            if (!user.getEnabled()) {
                throw new AccountException("账户未启用.");
            }
            if (user.getLocked()) {
                throw new LockedAccountException("账号被锁定.");
            }
            if (user.getExpired()) {
                throw new DisabledAccountException("账号已过期.");
            }
            byte[] salt = EncodeUtil.decodeHex(user.getPassword().substring(0, 16));
            return new SimpleAuthenticationInfo(
                    new Principal(user),
                    user.getPassword().substring(16),
                    ByteSource.Util.bytes(salt), getName());
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
        Principal principal = (Principal) getAvailablePrincipal(principalCollection);
        principal = securityService.getAuthorizationPrincipal(principal);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        addRbacAuthorization(authorizationInfo, principal);
        return authorizationInfo;
    }

    private void addRbacAuthorization(SimpleAuthorizationInfo authorization, Principal principal) {
        authorization.addRoles(principal.getRoles());
        authorization.addStringPermissions(principal.getRights());
    }
}
