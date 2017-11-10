package com.mrdios.billiegen.system.dao;

import com.mrdios.billiegen.common.framework.dao.BaseDao;
import com.mrdios.billiegen.system.entity.Admin;

/**
 * @author CodePorter
 * @date 2017-09-30
 */
public interface AdminDao extends BaseDao<Admin, String> {
    Admin findAdminByUsernameEquals(String username);
}
