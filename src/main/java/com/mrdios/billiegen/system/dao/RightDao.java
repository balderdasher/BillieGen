package com.mrdios.billiegen.system.dao;

import com.mrdios.billiegen.common.framework.dao.BaseDao;
import com.mrdios.billiegen.system.entity.Right;

/**
 * @author mrdios
 * @date 2017-10-15
 */
public interface RightDao extends BaseDao<Right, String> {
    Right findRightByRightNameEquals(String rightName);

    Right findRightByRightCodeEquals(String rightCode);
}
