package com.billiegen.system.service;

import com.billiegen.common.framework.service.BaseService;
import com.billiegen.system.entity.Admin;

/**
 * @author CodePorter
 * @date 2017-12-01
 */
public interface AdminService extends BaseService<Admin, String> {
    Admin findByUsername(String username);
}
