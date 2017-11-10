package com.mrdios.billiegen.system.dao;

import com.mrdios.billiegen.common.framework.dao.BaseDao;
import com.mrdios.billiegen.system.entity.Role;

/**
 * @author mrdios
 * @date 2017-10-14
 */
public interface RoleDao extends BaseDao<Role, String> {
    Role findRoleByRoleNameEquals(String name);
}
