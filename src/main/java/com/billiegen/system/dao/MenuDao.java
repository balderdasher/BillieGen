package com.billiegen.system.dao;

import com.billiegen.common.framework.dao.BaseDao;
import com.billiegen.system.entity.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author mrdios
 * @date 2017-10-15
 */
public interface MenuDao extends BaseDao<Menu, String> {
    Menu findByMenuName(String menuName);

    Menu findByMenuCode(String menuCode);

    List<Menu> findByMenuLevel(Integer menuLevel);

    default String getNextMenuCode(Integer menuLevel) {
        Long code = getMaxMenuCode(menuLevel);
        return code == null ? menuLevel + "001" : (code + 1) + "";
    }

    @Query("select max(menuCode) as nextCode from Menu where menuLevel=:menuLevel")
    Long getMaxMenuCode(@Param("menuLevel") Integer menuLevel);
}
