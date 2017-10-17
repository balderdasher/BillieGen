package com.billiegen.system.dao;

import com.billiegen.common.framework.dao.BaseDao;
import com.billiegen.system.entity.Admin;

/**
 * @author CodePorter
 * @date 2017-09-30
 */
public interface AdminDao extends BaseDao<Admin, String> {
    Admin findAdminByUsernameEquals(String username);
}
