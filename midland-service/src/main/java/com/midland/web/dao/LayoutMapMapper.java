package com.midland.web.dao;

import com.midland.web.model.LayoutMap;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LayoutMapMapper {

    LayoutMap selectLayoutMapById(Integer layoutMap);

    int deleteLayoutMapById(Integer layoutMap);

    int updateLayoutMapById(LayoutMap layoutMap);

    int insertLayoutMap(LayoutMap layoutMap);

    List<LayoutMap> findLayoutMapList(LayoutMap layoutMap);

    LayoutMap shiftUp(LayoutMap layoutMap);

    LayoutMap shiftDown(LayoutMap layoutMap);

}
