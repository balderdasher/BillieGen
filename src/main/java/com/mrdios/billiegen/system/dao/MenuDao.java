package com.mrdios.billiegen.system.dao;

import com.mrdios.billiegen.common.framework.dao.BaseDao;
import com.mrdios.billiegen.system.entity.Menu;

/**
 * @author mrdios
 * @date 2017-10-15
 */
public interface MenuDao extends BaseDao<Menu, String> {
    Menu findMenuByMenuNameEquals(String menuName);
}
