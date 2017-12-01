package com.billiegen.system.service;

import com.billiegen.common.framework.service.BaseService;
import com.billiegen.common.security.shiro.bean.Principal;
import com.billiegen.system.entity.Admin;

/**
 * @author CodePorter
 * @date 2017-12-01
 */
public interface SecurityService extends BaseService<Admin, String> {
    Principal getLoginPrincipal();

    Principal getAuthorizationPrincipal(Principal principal);
}
