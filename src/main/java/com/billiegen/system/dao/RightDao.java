package com.billiegen.system.dao;

import com.billiegen.common.framework.dao.BaseDao;
import com.billiegen.system.entity.Right;
import org.springframework.data.jpa.repository.Query;

/**
 * @author mrdios
 * @date 2017-10-15
 */
public interface RightDao extends BaseDao<Right, String> {
    Right findByRightName(String rightName);

    Right findByRightCode(String rightCode);

    Right findByRightLink(String rightLink);

    default String getNextRightCode() {
        Long code = getMaxRightCode();
        return code == null ? "4001" : (code + 1) + "";
    }

    @Query("select max(rightCode) as nextCode from Right")
    Long getMaxRightCode();
}
