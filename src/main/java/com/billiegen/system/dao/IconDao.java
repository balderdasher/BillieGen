package com.billiegen.system.dao;

import com.billiegen.common.framework.dao.BaseDao;
import com.billiegen.system.entity.Icon;

import java.util.List;

/**
 * @author CodePorter
 * @date 2017-12-07
 */
public interface IconDao extends BaseDao<Icon, String> {
    Icon findByDisplayName(String displayName);

    Icon findByClassName(String className);

    List<Icon> findBySourceType(String sourceType);

    List<Icon> findByIconType(String iconType);

}
