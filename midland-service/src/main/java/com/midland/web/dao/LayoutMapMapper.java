package com.midland.web.dao;

import com.midland.web.model.LayoutMap;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LayoutMapMapper {

    LayoutMap selectLayoutMapById(Integer layoutMap);

    int deleteLayoutMapById(Integer layoutMap);

    int updateLayoutMapById(LayoutMap layoutMap);

    Integer getMaxOrderBy(LayoutMap layoutMap);

    int insertLayoutMap(LayoutMap layoutMap);

    List<LayoutMap> findLayoutMapList(LayoutMap layoutMap);

}
