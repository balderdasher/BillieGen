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
     * 授权：无授权缓存时调用此方法以获取授权信息，理论上只会调用一次，之后的授权信息将会从缓存中获取
     *
     * @param principalCollection
     * @return
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Principal principal = securityService.doGetAuthorizedPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        addRbacAuthorization(authorizationInfo, principal);
        principal.setAuthorized(true);
        return authorizationInfo;
    }

    private void addRbacAuthorization(SimpleAuthorizationInfo authorization, Principal principal) {
        authorization.addRoles(principal.getRoles());
        authorization.addStringPermissions(principal.getRights());
    }

    /**
     * 获取授权信息：无授权缓存时将会调用{@link AuthorizingRealm#doGetAuthorizationInfo(PrincipalCollection)}
     * 方法读取授权信息并放入缓存中，之后从缓存中获取
     * <p>
     * 修改为<code>public</code>以便手动调用获取授权并缓存
     *
     * @param principals principals
     * @return 从数据库获得并设置的角色、权限、菜单等信息
     */
    @Override
    public AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        return super.getAuthorizationInfo(principals);
    }

    /**
     * 清空授权缓存，改为public以便在别处手动调用，如重新设置管理员角色、权限、菜单时清空缓存，再次获取授权时会重新调用
     * {@link AuthorizingRealm#doGetAuthorizationInfo(PrincipalCollection)}方法从数据库读取并设置授权信息
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        Principal principal = securityService.getLoginPrincipal();
        principal.setAuthorized(false);
    }
}
