package com.billiegen.system.service;

import com.billiegen.common.framework.service.BaseService;
import com.billiegen.common.security.shiro.bean.Principal;
import com.billiegen.system.entity.Admin;

/**
 * @author CodePorter
 * @date 2017-12-01
 */
public interface SecurityService extends BaseService<Admin, String> {
    /**
     * 获取登录身份
     *
     * @return {@link Principal}
     */
    Principal getLoginPrincipal();

    /**
     * 登录身份是否已经过授权
     *
     * @return true if authorized, false otherwise.
     */
    Boolean isPrincipalAuthorized();

    /**
     * 获取授权后的身份信息，设置登录身份具有的权限、角色、菜单并返回，之后统一从中取出使用
     *
     */
    Principal doGetAuthorizedPrincipal();
}
